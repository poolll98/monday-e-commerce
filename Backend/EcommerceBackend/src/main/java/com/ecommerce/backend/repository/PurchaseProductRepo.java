package com.ecommerce.backend.repository;

import com.ecommerce.backend.models.Purchase;
import com.ecommerce.backend.models.PurchaseProduct;

import org.springframework.data.jpa.repository.JpaRepository;


public interface PurchaseProductRepo extends JpaRepository <PurchaseProduct, Long> {
    


}