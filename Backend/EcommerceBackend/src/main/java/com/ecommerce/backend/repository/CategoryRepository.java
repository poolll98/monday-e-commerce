package com.ecommerce.backend.repository;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;

import com.ecommerce.backend.models.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ecommerce.backend.models.Product;

@Repository
public interface CategoryRepository extends JpaRepository<ProductCategory, Long>{
    Optional<ProductCategory> findById(Long Id);
    @Query(value = "SELECT * FROM public.product_category WHERE category_name =:category_name", nativeQuery = true)
    List<ProductCategory> findProductCategoryByCategory_name(@Param("category_name") String category_name);
}
