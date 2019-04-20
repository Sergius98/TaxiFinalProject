package com.training.model.entity;

import java.util.Optional;
import java.util.ResourceBundle;

public class Discount {
    private int id;
    private Optional<Integer> CarClass = Optional.empty();
    private Optional<Integer> SourceStreetId = Optional.empty();
    private Optional<Integer> DestinationStreetId = Optional.empty();
    private Optional<Long> MinimalBill = Optional.empty();
    private Optional<Long> MinimalThreshold = Optional.empty();
    private long discount = 0;

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

    public Optional<Long> getMinimalBill() {
        return MinimalBill;
    }

    public void setMinimalBill(Long minimalBill) {
        MinimalBill = Optional.of(minimalBill);
    }

    public Optional<Long> getMinimalThreshold() {
        return MinimalThreshold;
    }

    public void setMinimalThreshold(Long minimalThreshold) {
        MinimalThreshold = Optional.of(minimalThreshold);
    }

    public long getDiscount() {
        return discount;
    }

    public void setDiscount(long discount) {
        this.discount = discount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
