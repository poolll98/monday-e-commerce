package com.ecommerce.backend.controllers;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import com.ecommerce.backend.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/user")

public class UserController {

    @Autowired
    ProductRepository productObject;
   
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/getproduct/{name}")
    public ResponseEntity<?> GetProductbyName(@PathVariable String name) {
        System.out.println("Product "+name);
        return ResponseEntity.ok(productObject.findByName(name).get().getName());
      }
}
