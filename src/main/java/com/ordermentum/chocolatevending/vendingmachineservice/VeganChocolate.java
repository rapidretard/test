package com.ordermentum.chocolatevending.vendingmachineservice;

public class VeganChocolate extends Chocolate {

    private String name;
    private int price;

    public VeganChocolate(String name, int price) {
        this.setType("Vegan");
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "VeganChocolate{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
