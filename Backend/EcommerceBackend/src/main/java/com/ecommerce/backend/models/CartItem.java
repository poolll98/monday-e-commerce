package com.ecommerce.backend.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Min;

@Entity
@Table(name = "cart_item")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message="You need a minimum quantity.")
    @Min(value=1, message="")
    private Integer quantity;

    @NotNull(message = "")
   // @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "shopping_cart.id")
    private ShoppingCart shoppingCart;

    @NotNull(message = "")
    @ManyToOne(fetch = FetchType.EAGER)
    //@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "product.id")
    private Product product;

    public CartItem(){

    }

    public CartItem(int quantity, ShoppingCart shoppingCart, Product product) {
        this.quantity = quantity;
        this.shoppingCart = shoppingCart;
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
