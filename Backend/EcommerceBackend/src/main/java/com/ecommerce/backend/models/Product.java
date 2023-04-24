package com.ecommerce.backend.models;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@NotBlank(message = "")
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_category.id")
    private ProductCategory productCategory;

    @NotEmpty(message = "")
    @Size(max = 500, message = "")
    private String description;

    @Lob
    @Type(type="org.hibernate.type.BinaryType")
    private byte[] media;

    @NotNull(message = "")
    private Boolean instock;

    @NotNull(message = "")
    private Float price;

    @NotEmpty(message ="Product must have a name!")
    private String name;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "login_user.id")
    private User seller;

    public Product(){

    }

    public Product(String description, ProductCategory productCategory, byte[] media, Boolean instock, Float price,
                   String name, User seller) {
        this.description = description;
        this.productCategory = productCategory;
        this.media = media;
        this.instock = instock;
        this.price = price;
        this.name = name;
        this.seller = seller;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getMedia() {
        return media;
    }

    public void setMedia(byte[] media) {
        this.media = media;
    }

    public Boolean getInstock() {
        return instock;
    }

    public void setInstock(Boolean instock) {
        this.instock = instock;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }
}
