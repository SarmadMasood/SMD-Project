package com.example.smdproject;

public class Review {
    private String placeId;
    private String rating;
    private String user;
    private String review;
    private String date;

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public Review(){};

    public Review(String id, String rating, String user, String review, String date) {
        this.rating = rating;
        this.placeId = id;
        this.user = user;
        this.review = review;
        this.date = date;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
