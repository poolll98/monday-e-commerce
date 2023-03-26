package com.ecommerce.backend.models;

import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "login_user",
    uniqueConstraints = { 
      @UniqueConstraint(columnNames = "username")
    })
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

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

  private Date firstlogin;

  private Date lastlogin;

  private Boolean isbuyer;

  private Boolean isseller;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(  name = "user_roles",
       joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles = new HashSet<>();

  public User(){

  }
  public User(String username, String email, String password, String phone, String firstname, String lastname, Date firstlogin, Date lastlogin, Boolean isbuyer, Boolean isseller) {
    this.username = username;
    this.email = email;
    this.password = password;
    this.phone = phone;
    this.firstname = firstname;
    this.lastname = lastname;
    this.firstlogin = firstlogin;
    this.lastlogin = lastlogin;
    this.isbuyer = isbuyer;
    this.isseller = isseller;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

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

  public Date getFirstlogin() {
    return firstlogin;
  }

  public void setFirstlogin(Date firstlogin) {
    this.firstlogin = firstlogin;
  }

  public Date getLastlogin() {
    return lastlogin;
  }

  public void setLastlogin(Date lastlogin) {
    this.lastlogin = lastlogin;
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

  public Set<Role> getRoles() {
    return roles;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }

}
