package com.training.model.entity;

public class Taxi {
    private int id;
    private String name;
    private int carClass;
    private int streetId; // current location

    public Taxi(int id, String name, int carClass, int streetId) {
        this.id = id;
        this.name = name;
        this.carClass = carClass;
        this.streetId = streetId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCarClass() {
        return carClass;
    }

    public void setCarClass(int carClass) {
        this.carClass = carClass;
    }

    public int getStreetId() {
        return streetId;
    }

    public void setStreetId(int streetId) {
        this.streetId = streetId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
