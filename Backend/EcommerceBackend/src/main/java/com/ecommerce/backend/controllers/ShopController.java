package com.ecommerce.backend.controllers;

import java.util.List;

import com.ecommerce.backend.models.CartItem;
import com.ecommerce.backend.payload.request.AddCartItemRequest;
import com.ecommerce.backend.payload.response.MessageResponse;
import com.ecommerce.backend.repository.CartItemRepo;
import com.ecommerce.backend.repository.ShopCartRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;



@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/shopcart")
public class ShopController {

    @Autowired ShopCartRepo shopRepo;
    @Autowired CartItemRepo cartRepo;
    @GetMapping("/add")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> addCartItem(@Valid AddCartItemRequest item) {
       // if (cartRepo.existsById(id)) {
       //     return ResponseEntity.badRequest().body(new MessageResponse("Product already in the cart. Adjust quantity instead."));
       //     }
       // else {
            CartItem itemtoadd = new CartItem(item.getQuantity(),item.getCartObj(),item.getProdObj());
            
            cartRepo.save(itemtoadd);
      //  }
        return ResponseEntity.ok(new MessageResponse(item.toString()+" added to the cart."));
     }        

    @GetMapping("/remove")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public String removeCartItem() {
      return "User Content.";
    }

    @GetMapping("/editq")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public String editCartItem() {
      return "User Content.";
    }


}