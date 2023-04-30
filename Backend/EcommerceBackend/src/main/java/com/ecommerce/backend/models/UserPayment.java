package com.ecommerce.backend.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "user_payment")
public class UserPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "login_user.id")
    private User user;

    @NotBlank(message = "")
    private String payment_type;

    @NotBlank(message = "")
    private String name_on_card;

    @NotNull(message = "")
    @Size(min=13, max=16, message="")
    private String card_nr;

    @NotNull(message = "")
    @JsonFormat(pattern = "MM/yyyy")
    private String expiry_date;

    @NotNull(message = "")
    @Size(min = 3, max=5, message="")
    private String security_code;

    public UserPayment(){

    }

    public UserPayment(User user, String payment_type, String name_on_card, String card_nr, String expiry_date, String security_code) {
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
