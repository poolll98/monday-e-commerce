package com.ecommerce.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.backend.models.UserPayment;

@Repository
public interface UserPaymentRepo extends JpaRepository<UserPayment, Long> {
  
Optional<UserPayment> findById(Long paymentId);

}