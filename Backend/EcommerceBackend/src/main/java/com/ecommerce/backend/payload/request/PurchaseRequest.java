package com.ecommerce.backend.payload.request;

import org.hibernate.annotations.Type;

import javax.validation.constraints.NotNull;


public class PurchaseRequest {

    @NotNull(message = "An address is mandatory for the order.")
    private Long addressid;

    @NotNull(message = "A payment method is mandatory.")
    private Long paymentid;

    public Long getAddressId() {
        return addressid;
    }

    public void setAddressId(Long addressid) {
        this.addressid = addressid;
    }

    public Long getPaymentId() {
        return paymentid;
    }

    public void SetPaymentId(Long paymentid) {
        this.paymentid = paymentid;
    }

    public PurchaseRequest(Long paymentid, Long addressid) {
        this.paymentid = paymentid;
        this.addressid = addressid;
}

}

