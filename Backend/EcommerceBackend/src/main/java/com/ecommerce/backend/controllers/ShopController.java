package com.ecommerce.backend.controllers;

import java.util.List;

import com.ecommerce.backend.models.CartItem;
import com.ecommerce.backend.models.Product;
import com.ecommerce.backend.models.ShoppingCart;
import com.ecommerce.backend.payload.request.AddCartItemRequest;
import com.ecommerce.backend.payload.response.MessageResponse;
import com.ecommerce.backend.repository.CartItemRepo;
import com.ecommerce.backend.repository.ProductRepository;
import com.ecommerce.backend.repository.ShopCartRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;



@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/shopcart")
public class ShopController {

    @Autowired ShopCartRepo shopRepo;
    @Autowired CartItemRepo cartRepo;
    @Autowired ProductRepository prodRepo;

    @PostMapping("/add")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> addCartItem(@Valid @RequestBody AddCartItemRequest item) {

        String message = "";
        if (! shopRepo.existsById(item.getCartId())) {
            message = "Error: This cart doesn't exist. ";
        }
        if (! prodRepo.existsById(item.getProdId())){
            message += "Error: This product doesn't exist.";
        }
        if (message != ""){
            return ResponseEntity.badRequest().body(new MessageResponse(message));
        }

        ShoppingCart shopcart = shopRepo.findById(item.getCartId()).get();
        Product product = prodRepo.findById(item.getProdId()).get();

        System.out.println("cart: "+shopcart.getId() + "Prod: "+ product.getId());
       if (! cartRepo.findProductInTheCartById(shopcart.getId(),product.getId()).isEmpty()) {
           return ResponseEntity.badRequest().
                   body(new MessageResponse("Error: This Product is already in this cart."));
       }
        CartItem itemadd = new CartItem(item.getQuantity(), shopcart,  product);
        cartRepo.save(itemadd);

        return ResponseEntity.ok(new MessageResponse(item.getProdId().toString()+" added to the cart."));
     }        

    @DeleteMapping("/remove/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> removeCartItem(@PathVariable Long id) { //notice that this is the id of the CartItem not of the Product
        if(! cartRepo.existsById(id)){
            return ResponseEntity.badRequest().
                    body(new MessageResponse("Error: This Item is not present in the Cart."));
        }
        cartRepo.deleteById(id);
        return ResponseEntity.ok(new MessageResponse("Item has been removed from the cart."));
    }

    @GetMapping("/editq")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public String editCartItem() {
      return "User Content.";
    }

}