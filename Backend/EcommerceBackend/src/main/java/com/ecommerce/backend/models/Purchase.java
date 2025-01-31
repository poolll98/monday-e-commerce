package com.ecommerce.backend.models;


import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Min;

@Entity
@Table(name = "purchase")
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "login_user.id")
    private User user;

    @NotNull(message = "")
    private Date orderdate;

    @NotNull(message = "")
    private boolean orderstatus;

    @NotNull(message = "")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "addresses.id")
    private Address address;

    @NotNull(message = "")
    @Min(value=0, message="")
    private Float totalprice;

    @NotNull(message = "")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_payment.id")
    private UserPayment userPayment;

    @NotNull(message = "")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shopping_cart.id")
    private ShoppingCart shoppingCart;

    public Purchase(){

    }

    public Purchase(User user, Date orderdate, boolean orderstatus, Address address, Float totalprice, UserPayment userPayment, ShoppingCart shoppingCart) {
        this.user = user;
        this.orderdate = orderdate;
        this.orderstatus = orderstatus;
        this.address = address;
        this.totalprice = totalprice;
        this.userPayment = userPayment;
        this.shoppingCart = shoppingCart;
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

    public Date getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(Date orderdate) {
        this.orderdate = orderdate;
    }

    public boolean getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(boolean orderstatus) {
        this.orderstatus = orderstatus;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Float getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(Float totalprice) {
        this.totalprice = totalprice;
    }

    public UserPayment getUserPayment() {
        return userPayment;
    }

    public void setUserPayment(UserPayment userPayment) {
        this.userPayment = userPayment;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }


}
