package com.training.model.entity;

public class LoyaltyThreshold {
    private int id;
    private int threshold;
    private double discount;

    LoyaltyThreshold(int id, int threshold, double discount){
        this.id = id;
        this.threshold = threshold;
        this.discount = discount;
    }

    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
