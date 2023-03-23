package com.ecommerce.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.stereotype.Repository;

import com.ecommerce.backend.models.Product;
import com.ecommerce.backend.models.ShoppingCart;

@Repository

public interface ShopCartRepo extends JpaRepository<ShoppingCart, Long>{
    
}
