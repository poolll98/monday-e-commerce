package com.ecommerce.backend.controllers;
import com.ecommerce.backend.models.Product;
import com.ecommerce.backend.models.ProductCategory;
import com.ecommerce.backend.payload.response.MessageResponse;
import com.ecommerce.backend.payload.response.SearchProductMessage;
import com.ecommerce.backend.repository.CategoryRepository;
import com.ecommerce.backend.repository.ProductInStockRepository;
import com.ecommerce.backend.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired ProductRepository prodRepo;

    @Autowired
    CategoryRepository categoryRepo;

    @Autowired
    ProductInStockRepository prodStockRepo;


    @GetMapping("search/name/{name}")
    // public endpoint
    public ResponseEntity<?> searchProductByName(@PathVariable String name){
        List<Product> productList = prodRepo.findProductsByName(name);
        System.out.println(productList.size());
        List<SearchProductMessage> searchResult = new ArrayList<>();
        for(Product p: productList){
            String category_name  = p.getProductCategory().getCategory_name();
            SearchProductMessage m = new SearchProductMessage(p.getId(), p.getName(),
                    p.getDescription(), category_name, p.getMedia(), p.getInstock(),
                    p.getPrice());
            searchResult.add(m);

        }
        return ResponseEntity.ok(searchResult);
    }

    @GetMapping("search/category/{category_name}")
    public ResponseEntity<?> searchProductByCategory(@PathVariable String category_name){
        List<ProductCategory> productCategory = categoryRepo.findProductCategoryByCategory_name(category_name);
        if (! productCategory.isEmpty()){
            List<SearchProductMessage> searchResult = new ArrayList<>();
            List<Product> productList = prodRepo.findProductsByProductCategory(productCategory.get(0));
            for(Product p: productList){
                String name  = p.getProductCategory().getCategory_name();
                SearchProductMessage m = new SearchProductMessage(p.getId(), p.getName(),
                        p.getDescription(), name, p.getMedia(), p.getInstock(),
                        p.getPrice());
                searchResult.add(m);

            }
            return ResponseEntity.ok(searchResult);
        }
        return ResponseEntity.badRequest().body(new MessageResponse("This category doesn't exist."));
    }

    @GetMapping("search/instock/{instock}")
    public ResponseEntity<?> searchProductByStock(@PathVariable Boolean instock){
        List<Product> productInStock = prodStockRepo.findProductByStock(instock);
        if (! instock.booleanValue() ){
            List<SearchProductMessage> searchResult = new ArrayList<>();
            List<Product> stockProductList = prodStockRepo.findProductByStock(instock);
            for(Product p: stockProductList){
                String name  = p.getName();
                SearchProductMessage spm = new SearchProductMessage(p.getId(), p.getName(),
                        p.getDescription(), name, p.getMedia(), p.getInstock(),
                        p.getPrice());
                searchResult.add(spm);

            }
            return ResponseEntity.ok(searchResult);
        }
        return ResponseEntity.badRequest().body(new MessageResponse("In stock can only be true or false!"));
    }

}
