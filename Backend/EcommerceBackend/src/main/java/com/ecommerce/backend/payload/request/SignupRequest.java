package com.ecommerce.backend.payload.request;

import java.util.Set;

import javax.validation.constraints.*;

public class SignupRequest {
  @NotBlank(message = "Username is mandatory.")
  @Size(max = 100, message = "Username too long.")
  private String username;

  @NotBlank(message = "Email is mandatory.")
  @Size(max = 50, message = "Email too long.")
  @Email(message= "Bad Email format.")
  private String email;

  @NotBlank(message = "Password is mandatory.")
  @Size(min = 6, message= "Password must be long between 6 and 100 characters.")
  private String password;

  @Size(max=20, message="Invalid phone number.")
  private String phone;

  @NotBlank(message = "First name is mandatory.")
  private String firstname;

  @NotBlank(message = "Last name is mandatory.")
  private String lastname;

  @NotBlank(message = "You must specify if the user is a buyer.")
  private Boolean isbuyer;

  @NotBlank(message = "You must specify if the user is a seller.")
  private Boolean isseller;
  private Set<String> role;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

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
  public Set<String> getRole() {
    return this.role;
  }

  public void setRole(Set<String> role) {
    this.role = role;
  }
}
