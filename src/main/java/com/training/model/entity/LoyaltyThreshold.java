package com.training.model.entity;

public class LoyaltyThreshold {
    private int id;
    private long threshold;// TODO: 4/21/19 make long in db, make unique ind db 
    private double discount;

    public LoyaltyThreshold() {
    }

    public LoyaltyThreshold(Long threshold, Double discount) {
        this.threshold = threshold;
        this.discount = discount;
    }

    public long getThreshold() {
        return threshold;
    }

    public void setThreshold(long threshold) {
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
