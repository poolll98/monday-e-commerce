package com.ecommerce.backend.controllers;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import com.ecommerce.backend.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/user")

public class UserController {

    @Autowired
    ProductRepository productObject;

}
