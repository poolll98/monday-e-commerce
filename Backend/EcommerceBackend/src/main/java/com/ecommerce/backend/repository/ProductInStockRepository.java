package com.ecommerce.backend.repository;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.ecommerce.backend.models.Product;


@Repository
public interface ProductInStockRepository extends JpaRepository<Product, Boolean>{
    Optional<Product> findByInStock(Boolean inStock);
    @Query(value = "SELECT * FROM public.product WHERE instock=:inStock", nativeQuery = true)
    List<Product> findProductByStock(@Param("instock") Boolean inStock);
}






