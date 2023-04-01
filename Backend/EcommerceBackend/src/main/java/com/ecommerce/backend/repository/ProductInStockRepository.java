package com.ecommerce.backend.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.ecommerce.backend.models.Product;


@Repository
public interface ProductInStockRepository extends JpaRepository<Product, Boolean>{
    Optional<Product> findByInStock(Boolean instock);
    @Query(value = "SELECT * FROM public.product WHERE instock=:instock", nativeQuery = true)
    List<Product> findProductByStock(@Param("instock") Boolean instock);
}






