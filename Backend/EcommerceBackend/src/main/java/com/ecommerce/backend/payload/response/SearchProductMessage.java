package com.ecommerce.backend.payload.response;

import com.ecommerce.backend.models.ProductCategory;

public class SearchProductMessage{

    private String name;
    private String description;
    private String categoryName;
    private byte[] media;
    private Boolean instock;
    private Float price;

    public SearchProductMessage(String name, String description, String categoryName, byte[] media,
                                Boolean instock, Float price) {
        this.name = name;
        this.description = description;
        this.categoryName = categoryName;
        this.media = media;
        this.instock = instock;
        this.price = price;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
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
}
