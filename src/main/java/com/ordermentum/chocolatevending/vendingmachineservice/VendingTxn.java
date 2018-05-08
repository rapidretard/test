package com.ordermentum.chocolatevending.vendingmachineservice;

import com.google.common.collect.ImmutableList;

/**
 * Encapsulates a vending transaction
 *
 * @implNote (cop out class to avoid setting up a db tracked txn), however this will quickly
 * breakdown when you consider inventory stock and more controlled transactions, preferred way would be to use an embedded DB then
 */
public class VendingTxn {

    private int coinValueRemaining;
    private ImmutableList<VeganChocolate> availableChocolateOptions;
    private String optionalErrorMsg;

    public VendingTxn(int coinValueRemaining, ImmutableList<VeganChocolate> availableChocolateOptions, String optionalErrorMsg) {
        this.coinValueRemaining = coinValueRemaining;
        this.availableChocolateOptions = availableChocolateOptions;
        this.optionalErrorMsg = optionalErrorMsg;
    }

    public String getOptionalErrorMsg() {
        return optionalErrorMsg;
    }

    public void setOptionalErrorMsg(String optionalErrorMsg) {
        this.optionalErrorMsg = optionalErrorMsg;
    }

    public int getCoinValueRemaining() {
        return coinValueRemaining;
    }

    public void setCoinValueRemaining(int coinValueRemaining) {
        this.coinValueRemaining = coinValueRemaining;
    }

    public ImmutableList<VeganChocolate> getAvailableChocolateOptions() {
        return availableChocolateOptions;
    }

    public void setAvailableChocolateOptions(ImmutableList<VeganChocolate> availableChocolateOptions) {
        this.availableChocolateOptions = availableChocolateOptions;
    }

    @Override
    public String toString() {
        return "VendingTxn{" +
                "coinValueRemainig=" + coinValueRemaining +
                ", availableChocolateOptions=" + availableChocolateOptions +
                '}';
    }
}
