package com.ecommerce.backend.payload.request;
import javax.validation.constraints.NotNull;

public class UpdateQCartItemRequest {
    @NotNull
    private Long cartItemId;

    @NotNull
    private Integer quantity;

    public UpdateQCartItemRequest(Long cartItemId, Integer quantity) {
        this.cartItemId = cartItemId;
        this.quantity = quantity;
    }

    public Long getCartItemId() {
        return cartItemId;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
