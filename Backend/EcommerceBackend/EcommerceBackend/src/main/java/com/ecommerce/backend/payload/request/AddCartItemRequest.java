package com.ecommerce.backend.payload.request;

import javax.validation.constraints.NotNull;

public class AddCartItemRequest {
    
    @NotNull
    private Long cartid;

    @NotNull
    private Long prodid;

    @NotNull
    private Integer quantity;

    public AddCartItemRequest(Integer quantity, Long cartid, Long prodid) {
        this.quantity=quantity;
        this.cartid=cartid;
        this.prodid=prodid;
    }

    public Long getProdId() {
		return prodid;
	}

	public Long getCartId() {
		return cartid;
	}

	public Integer getQuantity() {
		return quantity;
	}

}
