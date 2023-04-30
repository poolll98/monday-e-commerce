package com.ecommerce.backend.payload.response;

public class SearchAddressMessage {

    private Long address_id;

    private String street;

    private int street_nr;

    private String city;

    private int postal_code;

    private String country;

    private String receiver;


    public SearchAddressMessage(Long address_id, String street, int street_nr, String city, int postal_code,
                                String country, String receiver) {
        this.address_id = address_id;
        this.street = street;
        this.street_nr = street_nr;
        this.city = city;
        this.postal_code = postal_code;
        this.country = country;
        this.receiver = receiver;
    }

    public Long getAddress_id() {
        return address_id;
    }

    public void setAddress_id(Long address_id) {
        this.address_id = address_id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getStreet_nr() {
        return street_nr;
    }

    public void setStreet_nr(int street_nr) {
        this.street_nr = street_nr;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(int postal_code) {
        this.postal_code = postal_code;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }
}
