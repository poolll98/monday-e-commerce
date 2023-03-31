package com.ecommerce.backend.controllers;
import com.ecommerce.backend.models.Product;
import com.ecommerce.backend.payload.response.SearchProductMessage;
import com.ecommerce.backend.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired ProductRepository prodRepo;

    //@Autowired
    //CategoryRepository categoryRepo;


    @GetMapping("search/{name}")
    // public endpoint
    public ResponseEntity<?> searchProductByName(@PathVariable String name){
        List<Product> productList = prodRepo.findProductsByName(name);
        System.out.println(productList.size());
        List<SearchProductMessage> searchResult = new ArrayList<>();
        for(Product p: productList){
            String category_name  = p.getProductCategory().getCategory_name();
            SearchProductMessage m = new SearchProductMessage(p.getName(),
                    p.getDescription(), category_name, p.getMedia(), p.getInstock(),
                    p.getPrice());
            searchResult.add(m);

        }
        return ResponseEntity.ok(searchResult);
    }

}
