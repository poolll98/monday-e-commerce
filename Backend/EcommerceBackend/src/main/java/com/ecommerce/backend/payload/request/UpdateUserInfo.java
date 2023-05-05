package com.ecommerce.backend.payload.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UpdateUserInfo {

    private String firstname;

    private String lastname;

    @Size(max=20, message="Invalid phone number.")
    private String phone;

    @Size(max = 50, message = "Email too long.")
    @Email(message= "Bad Email format.")
    private String email;

    private Boolean isbuyer;

    private Boolean isseller;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getIsbuyer() {
        return isbuyer;
    }

    public void setIsbuyer(Boolean isbuyer) {
        this.isbuyer = isbuyer;
    }

    public Boolean getIsseller() {
        return isseller;
    }

    public void setIsseller(Boolean isseller) {
        this.isseller = isseller;
    }
}
