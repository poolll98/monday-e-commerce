package com.ecommerce.backend.payload.request;

import javax.validation.constraints.NotNull;

import com.ecommerce.backend.models.Product;
import com.ecommerce.backend.models.ShoppingCart;

public class AddCartItemRequest {
    
    @NotNull
    private ShoppingCart cartobj;

    @NotNull
    private Product prodobj;

    @NotNull
    private Integer quantity;

    public AddCartItemRequest(Integer quantity, ShoppingCart cartobj, Product prodobj) {
        this.quantity=quantity;
        this.cartobj=cartobj;
        this.prodobj=prodobj;
    }

    public Product getProdObj() {
		return prodobj;
	}

	public ShoppingCart getCartObj() {
		return cartobj;
	}

	public Integer getQuantity() {
		return quantity;
	}

}
