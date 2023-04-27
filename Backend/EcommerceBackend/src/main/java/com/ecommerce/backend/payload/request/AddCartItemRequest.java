package com.ecommerce.backend.payload.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class AddCartItemRequest {

    @NotNull(message = "The id of the product is mandatory")
    private Long prodid;

    @NotNull(message = "Quantity is mandatory.")
    @Min(value=1, message = "Quantity must be at least 1.")
    private Integer quantity;

    public AddCartItemRequest(Integer quantity, Long prodid) {
        this.quantity=quantity;
        this.prodid=prodid;
    }

    public Long getProdId() {
		return prodid;
	}

	public Integer getQuantity() {
		return quantity;
	}

}
