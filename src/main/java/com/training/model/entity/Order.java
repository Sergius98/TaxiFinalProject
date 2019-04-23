package com.training.model.entity;

public class Order {
    int sourceStreetId;
    int destinationStreetId;
    int taxiCarClassId;

    double userThresholdsDiscount;
    long orderDiscountsSum;

    long orderPrice;


    public void setIds(int src, int dst, int car) {
        sourceStreetId = src;
        destinationStreetId = dst;
        taxiCarClassId = car;
    }

    public long getOrderDiscount() {
        return Math.min((((long) ((userThresholdsDiscount/100) * orderPrice)) + orderDiscountsSum), orderPrice);
    }

    public long getOrderFinalPrice(){
        long value = orderPrice - getOrderDiscount();
        return (value>1)?value:1;
    }

    public int getSourceStreetId() {
        return sourceStreetId;
    }

    public void setSourceStreetId(int sourceStreetId) {
        this.sourceStreetId = sourceStreetId;
    }

    public int getDestinationStreetId() {
        return destinationStreetId;
    }

    public void setDestinationStreetId(int destinationStreetId) {
        this.destinationStreetId = destinationStreetId;
    }

    public int getTaxiCarClassId() {
        return taxiCarClassId;
    }

    public void setTaxiCarClassId(int taxiCarClassId) {
        this.taxiCarClassId = taxiCarClassId;
    }

    public double getUserThresholdsDiscount() {
        return userThresholdsDiscount;
    }

    public void setUserThresholdsDiscount(double userThresholdsDiscount) {
        this.userThresholdsDiscount = userThresholdsDiscount;
    }

    public long getOrderDiscountsSum() {
        return orderDiscountsSum;
    }

    public void setOrderDiscountsSum(long orderDiscountsSum) {
        this.orderDiscountsSum = orderDiscountsSum;
    }


    public long getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(long orderPrice) {
        this.orderPrice = orderPrice;
    }

}
