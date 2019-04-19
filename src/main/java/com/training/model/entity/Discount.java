package com.training.model.entity;

import java.util.Optional;

public class Discount {
    private int id;
    private Optional<Integer> CarClass = Optional.empty();
    private Optional<Integer> SourceStreetId = Optional.empty();
    private Optional<Integer> DestinationStreetId = Optional.empty();
    private Optional<Integer> MinimalBill = Optional.empty();
    private Optional<Integer> MinimalThreshold = Optional.empty();
    private int discount;

    public Discount() {

    }
    public Discount(int id, int discount) {
        this.id = id;
        this.discount = discount;
    }

    public Optional<Integer> getCarClass() {
        return CarClass;
    }

    public void setCarClass(Integer carClass) {
        CarClass = Optional.of(carClass);
    }

    public Optional<Integer> getSourceStreetId() {
        return SourceStreetId;
    }

    public void setSourceStreetId(Integer sourceStreetId) {
        SourceStreetId = Optional.of(sourceStreetId);
    }

    public Optional<Integer> getDestinationStreetId() {
        return DestinationStreetId;
    }

    public void setDestinationStreetId(Integer destinationStreetId) {
        DestinationStreetId = Optional.of(destinationStreetId);
    }

    public Optional<Integer> getMinimalBill() {
        return MinimalBill;
    }

    public void setMinimalBill(Integer minimalBill) {
        MinimalBill = Optional.of(minimalBill);
    }

    public Optional<Integer> getMinimalThreshold() {
        return MinimalThreshold;
    }

    public void setMinimalThreshold(Integer minimalThreshold) {
        MinimalThreshold = Optional.of(minimalThreshold);
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
