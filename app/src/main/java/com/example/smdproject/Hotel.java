package com.example.smdproject;

public class Hotel {
    String name;
    String rating;
    String imgUrl;

    public Hotel(){};

    public Hotel(String name, String rating, String imgUrl) {
        this.name = name;
        this.rating = rating;
        this.imgUrl = imgUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
