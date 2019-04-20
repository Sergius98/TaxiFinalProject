package com.training.model.entity;

import java.util.Optional;

public class Discount {
    private int id;private Optional<Integer> carClass = Optional.empty();
    private Optional<Integer> sourceStreetId = Optional.empty();
    private Optional<Integer> destinationStreetId = Optional.empty();
    private Optional<Long> minimalBill = Optional.empty();
    private Optional<Long> minimalThreshold = Optional.empty();
    private long discount = 0;

    // constructors

    public Discount() {

    }
    public Discount(int id, int discount) {
        this.id = id;
        this.discount = discount;
    }

    public Discount(Optional<Integer> carClass, Optional<Integer> sourceStreetId,
                    Optional<Integer> destinationStreetId,
                    Optional<Long> minimalBill, Optional<Long> minimalThreshold,
                    long discount) {
        this.carClass = carClass;
        this.sourceStreetId = sourceStreetId;
        this.destinationStreetId = destinationStreetId;
        this.minimalBill = minimalBill;
        this.minimalThreshold = minimalThreshold;
        this.discount = discount;
    }

    // getters/setters

    public Optional<Integer> getCarClass() {
        return carClass;
    }

    public void setCarClass(Integer carClass) {
        this.carClass = Optional.of(carClass);
    }

    public Optional<Integer> getSourceStreetId() {
        return sourceStreetId;
    }

    public void setSourceStreetId(Integer sourceStreetId) {
        this.sourceStreetId = Optional.of(sourceStreetId);
    }

    public Optional<Integer> getDestinationStreetId() {
        return destinationStreetId;
    }

    public void setDestinationStreetId(Integer destinationStreetId) {
        this.destinationStreetId = Optional.of(destinationStreetId);
    }

    public Optional<Long> getMinimalBill() {
        return minimalBill;
    }

    public void setMinimalBill(Long minimalBill) {
        this.minimalBill = Optional.of(minimalBill);
    }

    public Optional<Long> getMinimalThreshold() {
        return minimalThreshold;
    }

    public void setMinimalThreshold(Long minimalThreshold) {
        this.minimalThreshold = Optional.of(minimalThreshold);
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
