package com.ecommerce.backend.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message="")
    private String street;

    @NotNull(message="")
    private int street_nr;

    @NotBlank(message="")
    private String city;

    @NotBlank(message="")
    private String region;

    @NotBlank(message="")
    private String country;

    @NotBlank(message="")
    private String receiver;

    public Address(){

    }

    public Address(String street, int street_nr, String city, String region, String country, String receiver) {
        this.street = street;
        this.street_nr = street_nr;
        this.city = city;
        this.region = region;
        this.country = country;
        this.receiver = receiver;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
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
