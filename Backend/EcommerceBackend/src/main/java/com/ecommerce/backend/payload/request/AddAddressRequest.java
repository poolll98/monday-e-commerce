package com.ecommerce.backend.payload.request;

import javax.validation.constraints.NotNull;

public class AddAddressRequest {

    @NotNull(message="city is mandatory.")
    private String city;

    @NotNull(message="country is mandatory.")
    private String country;

    @NotNull(message="the receiver is mandatory.")
    private String receiver;

    @NotNull(message="postal code is mandatory.")
    private Integer postal_code;

    @NotNull(message="street is mandatory.")
    private String street;

    @NotNull(message="street number is mandatory.")
    private Integer street_nr;


    public AddAddressRequest(String city, String country, String receiver, Integer postal_code, String street, Integer street_nr
                            ) {
        this.city = city;
        this.country = country;
        this.receiver = receiver;
        this.postal_code = postal_code;
        this.street = street;
        this.street_nr = street_nr;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getReceiver() {
        return receiver;
    }

    public Integer getPostal_code() {return postal_code;}

    public String getStreet() {
        return street;
    }

    public Integer getStreet_nr() {
        return street_nr;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public void setPostal_code(Integer postal_code) {
        this.postal_code = postal_code;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setStreet_nr(Integer street_nr) {
        this.street_nr = street_nr;
    }

}

