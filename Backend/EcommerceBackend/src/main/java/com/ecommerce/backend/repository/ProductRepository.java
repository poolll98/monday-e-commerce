package com.ecommerce.backend.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ecommerce.backend.models.Product;
    
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
  //@Query(value = "SELECT * FROM product WHERE product.name =:name", nativeQuery = true)
  List<Product> findProductsByName(String name);
  Optional<Product> findById(Long id);
  boolean existsById(Long id);
}
