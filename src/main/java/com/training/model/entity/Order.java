package com.training.model.entity;

public class Order {
    int sourceStreetId;
    int srcX;
    int srcY;

    int destinationStreetId;
    int dstX;
    int dstY;

    int taxiCarClassId;
    long taxiCarClassPrice;
    int taxiId;

    long userSpendMoney;
    double userThresholdsDiscount;
    long orderDiscountsSum;

    long orderDiscount;
    long orderPrice;

    public int getSourceStreetId() {
        return sourceStreetId;
    }

    public void setSourceStreetId(int sourceStreetId) {
        this.sourceStreetId = sourceStreetId;
    }

    public int getSrcX() {
        return srcX;
    }

    public void setSrcX(int srcX) {
        this.srcX = srcX;
    }

    public int getSrcY() {
        return srcY;
    }

    public void setSrcY(int srcY) {
        this.srcY = srcY;
    }

    public int getDestinationStreetId() {
        return destinationStreetId;
    }

    public void setDestinationStreetId(int destinationStreetId) {
        this.destinationStreetId = destinationStreetId;
    }

    public int getDstX() {
        return dstX;
    }

    public void setDstX(int dstX) {
        this.dstX = dstX;
    }

    public int getDstY() {
        return dstY;
    }

    public void setDstY(int dstY) {
        this.dstY = dstY;
    }

    public int getTaxiCarClassId() {
        return taxiCarClassId;
    }

    public void setTaxiCarClassId(int taxiCarClassId) {
        this.taxiCarClassId = taxiCarClassId;
    }

    public long getTaxiCarClassPrice() {
        return taxiCarClassPrice;
    }

    public void setTaxiCarClassPrice(long taxiCarClassPrice) {
        this.taxiCarClassPrice = taxiCarClassPrice;
    }

    public int getTaxiId() {
        return taxiId;
    }

    public void setTaxiId(int taxiId) {
        this.taxiId = taxiId;
    }

    public long getUserSpendMoney() {
        return userSpendMoney;
    }

    public void setUserSpendMoney(long userSpendMoney) {
        this.userSpendMoney = userSpendMoney;
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

    public long getOrderDiscount() {
        return orderDiscount;
    }

    public void setOrderDiscount(long orderDiscount) {
        this.orderDiscount = orderDiscount;
    }

    public long getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(long orderPrice) {
        this.orderPrice = orderPrice;
    }
}
