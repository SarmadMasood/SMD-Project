package com.example.smdproject;

public class Flight {
    String carrier;
    String price;
    String date;

    public Flight(String carrier, String price, String date) {
        this.carrier = carrier;
        this.price = price;
        this.date = date;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
