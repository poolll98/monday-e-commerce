package com.ecommerce.backend.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "user_addresses")
public class UserAddress{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "login_user.id")
    private User user;

    @NotNull(message = "")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "addresses.id")
    private Address address;

    private Boolean defaultaddr;

    public UserAddress(){

    }

    public UserAddress(User user, Address address, Boolean defaultaddr) {
        this.user = user;
        this.address = address;
        this.defaultaddr = defaultaddr;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Boolean getDefaultaddr() {
        return defaultaddr;
    }

    public void setDefaultaddr(Boolean defaultaddr) {
        this.defaultaddr = defaultaddr;
    }
}
