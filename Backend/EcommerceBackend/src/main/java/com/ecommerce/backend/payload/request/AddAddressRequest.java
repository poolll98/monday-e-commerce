package com.ecommerce.backend.payload.request;

import javax.validation.constraints.NotNull;

public class AddAddressRequest {

    @NotNull
    private String city;

    @NotNull
    private String country;

    @NotNull
    private String receiver;

    @NotNull
    private String region;

    @NotNull
    private String street;

    @NotNull
    private Integer street_nr;

    private boolean defaultaddr;

    public AddAddressRequest(String city, String country, String receiver, String region, String street, Integer street_nr,
                             boolean defaultaddr) {
        this.city = city;
        this.country = country;
        this.receiver = receiver;
        this.region = region;
        this.street = street;
        this.street_nr = street_nr;
        this.defaultaddr = defaultaddr;
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

    public String getRegion() {
        return region;
    }

    public String getStreet() {
        return street;
    }

    public Integer getStreet_nr() {
        return street_nr;
    }

    public boolean isDefaultaddr() {
        return defaultaddr;
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

    public void setRegion(String region) {
        this.region = region;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setStreet_nr(Integer street_nr) {
        this.street_nr = street_nr;
    }

    public void setDefaultaddr(boolean defaultaddr) {
        this.defaultaddr = defaultaddr;
    }
}

