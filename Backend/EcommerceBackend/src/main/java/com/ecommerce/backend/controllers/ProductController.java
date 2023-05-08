package com.ecommerce.backend.controllers;
import com.ecommerce.backend.models.Product;
import com.ecommerce.backend.models.ProductCategory;
import com.ecommerce.backend.models.User;
import com.ecommerce.backend.payload.request.AddProductRequest;
import com.ecommerce.backend.payload.request.UpdateProductInfo;
import com.ecommerce.backend.payload.response.AddElementMessage;
import com.ecommerce.backend.payload.response.MessageResponse;
import com.ecommerce.backend.payload.response.MessageWithList;
import com.ecommerce.backend.payload.response.SearchProductMessage;
import com.ecommerce.backend.repository.CategoryRepository;
import com.ecommerce.backend.repository.ProductRepository;

import com.ecommerce.backend.repository.UserRepository;
import com.ecommerce.backend.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    UserRepository userRepository;

    @Autowired 
    ProductRepository prodRepo;

    @Autowired
    CategoryRepository categoryRepo;

    @Autowired
    private JwtUtils jwtUtils;


    @GetMapping("/search/name/{name}")
    // public endpoint
    public ResponseEntity<?> searchProductByName(@PathVariable String name){
        List<Product> searchResult = prodRepo.findProductsByName(name);
        System.out.println("This many products found by name: "+searchResult.size());
        return ResponseEntity.ok(this.prepareOutputMessage(searchResult));
    }

    @GetMapping("/{id}")
    // public endpoint
    public ResponseEntity<?> searchProductByName(@PathVariable Long id){
        Optional<Product> searchResult = prodRepo.findById(id);
        if(searchResult.isPresent()){
            Product p = searchResult.get();
            String category_name  = p.getProductCategory().getCategory_name();
            Long seller_id = p.getSeller().getId();
            SearchProductMessage spm = new SearchProductMessage(p.getId(), p.getName(),
                    p.getDescription(), category_name, p.getMedia(), p.getInstock(),
                    p.getPrice(), seller_id);
            return ResponseEntity.ok(spm);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search/category/{category_name}")
    // public endpoint
    public ResponseEntity<?> searchProductByCategory(@PathVariable String category_name){
        List<ProductCategory> productCategory = categoryRepo.findProductCategoryByCategory_name(category_name);
        if (! productCategory.isEmpty()){
            List<SearchProductMessage> searchResult = new ArrayList<>();
            List<Product> productList = prodRepo.findProductsByProductCategory(productCategory.get(0));
            for(Product p: productList){
                String name  = p.getProductCategory().getCategory_name();
                Long seller_id = p.getSeller().getId();
                SearchProductMessage m = new SearchProductMessage(p.getId(), p.getName(),
                        p.getDescription(), name, p.getMedia(), p.getInstock(),
                        p.getPrice(), seller_id);
                searchResult.add(m);

            }
            return ResponseEntity.ok(searchResult);
        }
        return ResponseEntity.badRequest().body(new MessageResponse("This category doesn't exist."));
    }

    @GetMapping("/search/instock/{instock}")
    // public endpoint
    public ResponseEntity<?> searchProductByStock(@PathVariable Boolean instock){
        List<Product> searchResult = prodRepo.findProductsByInstock(instock);
        System.out.println("This many products in stock: "+searchResult.toString());
        return ResponseEntity.ok(this.prepareOutputMessage(searchResult));
    }

    @GetMapping("/category")
    // public endpoint
    public ResponseEntity<?> getAvailableCategories() {
        List<ProductCategory> allCategories = categoryRepo.getAll();
        List<String> categoryNames = allCategories.stream().map(ProductCategory::getCategory_name).
                distinct().toList();
        return ResponseEntity.ok(categoryNames);
    }


    @PostMapping("/add")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> addProduct(@Valid @RequestBody AddProductRequest productRequest,
                                        @RequestHeader(name = "Authorization") String token){

        token = token.substring(7); //we just drop the word "bearer" from the token's signature
        User currentUser = userRepository.findByUsername(jwtUtils.getUserNameFromJwtToken(token)).get();
        if(! currentUser.getIsseller()){
            return ResponseEntity.badRequest().
                    body(new MessageResponse("Error: only a seller user can add a product."));
        }
        List<ProductCategory> productCategory = categoryRepo.findProductCategoryByCategory_name(
                productRequest.getCategory_name());
        if (productCategory.isEmpty()){
            List<ProductCategory> allCategories = categoryRepo.getAll();
            List<String> categoryNames = allCategories.stream().map(ProductCategory::getCategory_name).
                    distinct().toList();
            return ResponseEntity.badRequest().
                    body(new MessageWithList("Error: This category doesn't exist. Available categories:",
                            categoryNames));
        }
        else {
            ProductCategory category = productCategory.get(0);
            Product newProduct = new Product(productRequest.getDescription(), category, productRequest.getMedia(),
                    productRequest.getInstock(), productRequest.getPrice(), productRequest.getName(), currentUser);
            this.prodRepo.save(newProduct);
            List<Product> producList = prodRepo.findProductsBySeller(currentUser);
            Long product_id = producList.get(producList.size()-1).getId();
            return ResponseEntity.ok(new AddElementMessage("Product correctly added.", product_id));
        }
    }

    private List<SearchProductMessage> prepareOutputMessage(List<Product> searchResult){
        List<SearchProductMessage> outputResult = new ArrayList<>();
        for(Product p: searchResult){
            String category_name  = p.getProductCategory().getCategory_name();
            Long seller_id = p.getSeller().getId();
            SearchProductMessage spm = new SearchProductMessage(p.getId(), p.getName(),
                    p.getDescription(), category_name, p.getMedia(), p.getInstock(),
                    p.getPrice(), seller_id);
            outputResult.add(spm);

        }
        return outputResult;
    }

    @PutMapping("/edit/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> editProductInformation(@PathVariable Long id,
                                                    @Valid @RequestBody UpdateProductInfo updateProductInfo,
                                                    @RequestHeader(name = "Authorization") String token) {
        token = token.substring(7);
        User currentUser = this.userRepository.findByUsername(jwtUtils.getUserNameFromJwtToken(token)).get();
        Optional<Product> searchProduct = prodRepo.findById(id);
        if(searchProduct.isEmpty()){
            return ResponseEntity.badRequest().body(new MessageResponse("This product doesn't exist."));
        }
        else{
            Product product = searchProduct.get();
            if(! product.getSeller().getId().equals(currentUser.getId())){
                return ResponseEntity.badRequest().body(new MessageResponse("This product it is not sold by this user"));
            }
            else{
                boolean flag = false;
                if (updateProductInfo.getCategory_name()!= null && !updateProductInfo.getCategory_name().equals("")){
                    List<ProductCategory> productCategory = categoryRepo.findProductCategoryByCategory_name(
                            updateProductInfo.getCategory_name());
                    if (productCategory.isEmpty()) {
                        List<ProductCategory> allCategories = categoryRepo.getAll();
                        List<String> categoryNames = allCategories.stream().map(ProductCategory::getCategory_name).
                                distinct().toList();
                        return ResponseEntity.badRequest().
                                body(new MessageWithList("Error: This category doesn't exist. Available categories:",
                                        categoryNames));
                    }
                    else{
                        product.setProductCategory(productCategory.get(0));
                        flag = true;
                    }
                }
                if(updateProductInfo.getDescription()!=null && !updateProductInfo.getDescription().equals("")){
                    product.setDescription(updateProductInfo.getDescription());
                    flag = true;
                }
                if(updateProductInfo.getMedia() != null){
                    product.setMedia(updateProductInfo.getMedia());
                    flag = true;
                }
                if(updateProductInfo.getInstock() != null){
                    product.setInstock(updateProductInfo.getInstock());
                    flag = true;
                }
                if(updateProductInfo.getPrice() != null){
                    product.setPrice(updateProductInfo.getPrice());
                    flag = true;
                }
                if(updateProductInfo.getName()!= null && !updateProductInfo.getName().equals("")){
                    product.setName(updateProductInfo.getName());
                    flag = true;
                }
                if(flag) {
                    userRepository.save(currentUser);
                    return ResponseEntity.ok(new MessageResponse("Information correctly updated."));
                }
                else{
                    return ResponseEntity.ok(new MessageResponse("Nothing  to update."));
                }

            }
        }

    }

    @GetMapping("/soldby/{id}")
    // public endpoint
    public ResponseEntity<?> getSoldProductsOfASeller(@PathVariable Long id) {

        Optional<User> sellerSearch = userRepository.findById(id);
        if (sellerSearch.isEmpty()) {
            return ResponseEntity.badRequest().body(new MessageResponse("User: " + id.toString() + " doesn't exist."));
        } else {
            User seller = sellerSearch.get();
            if (!seller.getIsseller()) {
                return ResponseEntity.badRequest().body(new MessageResponse("User: " + id.toString() + " is not a seller."));
            } else {
                List<Product> producList = prodRepo.findProductsBySeller(seller);
                return ResponseEntity.ok(this.prepareOutputMessage(producList));
            }
        }
    }



}
