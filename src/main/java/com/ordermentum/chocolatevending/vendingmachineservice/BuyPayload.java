package com.ordermentum.chocolatevending.vendingmachineservice;

/**
 * DTO PoJo for buy payload
 */
public class BuyPayload {
    private String chocolateName;
    private int coinValue;

    public BuyPayload(String chocolateName, int coinValue) {
        this.chocolateName = chocolateName;
        this.coinValue = coinValue;
    }

    public BuyPayload() {
    }

    public String getChocolateName() {
        return chocolateName;
    }

    public void setChocolateName(String chocolateName) {
        this.chocolateName = chocolateName;
    }

    public int getCoinValue() {
        return coinValue;
    }

    public void setCoinValue(int coinValue) {
        this.coinValue = coinValue;
    }
}
