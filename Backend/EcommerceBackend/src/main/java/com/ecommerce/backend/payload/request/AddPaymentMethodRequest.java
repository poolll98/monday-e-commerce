package com.ecommerce.backend.payload.request;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AddPaymentMethodRequest {

    @NotNull(message = "The card's owner is mandatory.")
    private String name_on_card;

    @NotNull(message = "The number of the card is mandatory.")
    @Size(min=13, max=16, message="The lenghth of the number should be between 13-16.")
    private String card_nr;

    @NotNull(message = "The expire date is mandatory.")
    @JsonFormat(pattern = "MM/yyyy")
    private String expiry_date;

    @NotNull(message = "The security code is mandatory.")
    @Size(min = 3, max=5, message="The length of the code should be between 3-5;")
    private String security_code;

    public AddPaymentMethodRequest(String name_on_card, String card_nr, String expiry_date, String security_code) {
        this.name_on_card = name_on_card;
        this.card_nr = card_nr;
        this.expiry_date = expiry_date;
        this.security_code = security_code;
    }

    public String getName_on_card() {
        return name_on_card;
    }

    public void setName_on_card(String name_on_card) {
        this.name_on_card = name_on_card;
    }

    public String getCard_nr() {
        return card_nr;
    }

    public void setCard_nr(String card_nr) {
        this.card_nr = card_nr;
    }

    public String getExpiry_date() {
        return expiry_date;
    }

    public void setExpiry_date(String expiry_date) {
        this.expiry_date = expiry_date;
    }

    public String getSecurity_code() {
        return security_code;
    }

    public void setSecurity_code(String security_code) {
        this.security_code = security_code;
    }
}
