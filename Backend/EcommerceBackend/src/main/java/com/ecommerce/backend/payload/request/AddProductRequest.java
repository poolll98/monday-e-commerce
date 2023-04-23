package com.ecommerce.backend.payload.request;

import org.hibernate.annotations.Type;

import javax.persistence.Lob;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AddProductRequest {

    @NotNull(message = "category's name is mandatory.")
    private String category_name;

    @NotNull(message = "description is mandatory.")
    @Size(max = 500, message = "description must be less thant 500 characters.")
    private String description;

    @Lob
    @Type(type="org.hibernate.type.BinaryType")
    private byte[] media;

    @NotNull(message = "specify the availability of the product.")
    private Boolean instock;

    @NotNull(message = "price is mandatory.")
    @Min(value=0, message = "price may be positive!")
    private Float price;

    @NotEmpty(message ="The product must have a name!")
    private String name;

    public AddProductRequest(String category_name, String description, byte[] media, Boolean instock,
                             Float price, String name) {
        this.category_name = category_name;
        this.description = description;
        this.media = media;
        this.instock = instock;
        this.price = price;
        this.name = name;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
