package com.ecommerce.backend.repository;

import com.ecommerce.backend.models.Purchase;
import com.ecommerce.backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PurchaseRepository extends JpaRepository <Purchase, Long> {
    
 List<Purchase> findPurchasesByUser(User user);

}
