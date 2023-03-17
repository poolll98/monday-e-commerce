package com.ecommerce.backend.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "purchase_product")
public class PurchaseProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "purchase.id")
    private Purchase purchase;

    @NotBlank(message = "")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product.id")
    private Product product;

    public PurchaseProduct(){

    }

    public PurchaseProduct(Purchase purchase, Product product) {
        this.purchase = purchase;
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
