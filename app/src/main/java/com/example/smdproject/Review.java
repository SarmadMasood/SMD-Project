package com.example.smdproject;

public class Review {
    String user;
    String review;
    String date;

    public Review(String user, String review, String date) {
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
