package com.example.smdproject;

public class Contact {
    private String placeId;
    private String name;
    private String number;

    public Contact(){
    };

    public Contact(String id,String name, String number) {
        this.placeId =id;
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getId() {
        return placeId;
    }

    public void setId(String id) {
        this.placeId = id;
    }
}
