package com.ecommerce.backend.payload.request;

import org.hibernate.annotations.Type;

import javax.persistence.Lob;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public class UpdateProductInfo {

    private String category_name;

    @Size(max = 500, message = "description is too long.")
    private String description;

    @Lob
    @Type(type="org.hibernate.type.BinaryType")
    private byte[] media;

    private Boolean instock;

    @Min(value=0, message = "price may be positive!")
    private Float price;

    private String name;

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
