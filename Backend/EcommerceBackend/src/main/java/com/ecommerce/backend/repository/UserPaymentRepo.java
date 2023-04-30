package com.ecommerce.backend.repository;

import java.util.List;
import java.util.Optional;

import com.ecommerce.backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.backend.models.UserPayment;

@Repository
public interface UserPaymentRepo extends JpaRepository<UserPayment, Long> {
  
Optional<UserPayment> findById(Long paymentId);

List<UserPayment> findUserPaymentsByUser(User user);

}