package com.example.appdatveonline;

import com.google.gson.annotations.SerializedName;

public class Movies {
    @SerializedName("title")
    private String title;
    @SerializedName("price")
    private String price;
    @SerializedName("description")
    private String description;
    @SerializedName("image")
    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
