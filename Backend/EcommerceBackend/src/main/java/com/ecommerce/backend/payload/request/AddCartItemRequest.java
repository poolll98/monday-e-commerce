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
        System.out.println("So the quantity is "+quantity);
        this.cartid=cartid;
        System.out.println("So the cartid is "+cartid);
        this.prodid=prodid;
        System.out.println("So the prodid is "+prodid);
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
