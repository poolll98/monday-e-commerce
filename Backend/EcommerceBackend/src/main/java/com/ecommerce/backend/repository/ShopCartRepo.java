package com.ecommerce.backend.repository;

import java.util.List;
import java.util.Optional;

import com.ecommerce.backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.backend.models.ShoppingCart;

@Repository

public interface ShopCartRepo extends JpaRepository<ShoppingCart, Long>{
    Optional<ShoppingCart> findById(Long id);

    List<ShoppingCart> findByIsActiveAndUser(Boolean isActive, User user);
    boolean existsById(Long id);
}
