package com.ecommerce.backend.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "orderstatus")
public class OrderStatus {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "")
    @Size(max = 500, message = "")
    private String status;

    private Date ordertime;

    public OrderStatus(){

    }

    public OrderStatus(String status, Date ordertime) {
        this.status = status;
        this.ordertime = ordertime;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getOrderTime() {
        return ordertime;
    }

    public void setOrderTime(Date ordertime) {
        this.ordertime = ordertime;
    }

}








