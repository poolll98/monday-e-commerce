package com.ecommerce.backend.services.util;

import com.ecommerce.backend.models.Address;

import java.util.Date;

public class OrderEmailData {

    private String sellerEmail;
    private Long transactionNumber;
    private Date transactionDate;
    private String firstName;
    private String lastName;
    private Address address;

    private String buyerEmail;
    private String productName;
    private Integer quantity;
    private Double price;

    public OrderEmailData(){};
    public OrderEmailData(String sellerEmail, Date transactionDate, String firstName, String lastName,
                          Address address, String buyerEmail, String productName, Integer quantity, double price) {
        this.sellerEmail = sellerEmail;
        this.transactionDate = transactionDate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.buyerEmail = buyerEmail;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }

    public Long getTransactionNumber() {
        return transactionNumber;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Address getAddress() {
        return address;
    }

    public String getProductName() {
        return productName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Double getPrice() {
        return price;
    }

    public String getSellerEmail() {
        return sellerEmail;
    }

    public String getBuyerEmail() {
        return buyerEmail;
    }

    public void setTransactionNumber(Long transactionNumber) {
        this.transactionNumber = transactionNumber;
    }
}
