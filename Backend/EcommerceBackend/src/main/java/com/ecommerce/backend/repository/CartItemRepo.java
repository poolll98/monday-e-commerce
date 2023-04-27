package com.ecommerce.backend.repository;


import java.util.List;
import java.util.Optional;

import com.ecommerce.backend.models.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ecommerce.backend.models.CartItem;


@Repository

public interface CartItemRepo extends JpaRepository<CartItem, Long>{
    Optional<CartItem> findById(Long id);
    boolean existsById(Long id);

    Integer countDistinctByShoppingCart(ShoppingCart shoppingCart);

    @Query(value = "SELECT * FROM public.cart_item WHERE product_id =:productId AND shopping_cart_id =:cartId", nativeQuery = true)
    List<CartItem> findProductInTheCartById(@Param("cartId") Long cartId, @Param("productId") Long productId);
    void deleteById(Long id);
}
