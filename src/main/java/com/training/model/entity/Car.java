package com.training.model.entity;

public class Car {
    private int id; // car class
    private String name;
    private long price; // price per 1 standard units, in cents

    public Car(int id, String name, long price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    //getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }
}
