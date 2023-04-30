package com.ecommerce.backend.payload.response;


public class SearchPaymentMessage {

    private Long id;
    private String payment_type;
    private String name_on_card;
    private String card_nr;
    private String expiry_date;
    private String security_code;

    public SearchPaymentMessage(Long id, String payment_type, String name_on_card, String card_nr, String expiry_date,
                                String security_code) {
        this.id = id;
        this.payment_type = payment_type;
        this.name_on_card = name_on_card;
        this.card_nr = card_nr;
        this.expiry_date = expiry_date;
        this.security_code = security_code;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPayment_type() {
        return payment_type;
    }

    public void setPayment_type(String payment_type) {
        this.payment_type = payment_type;
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
