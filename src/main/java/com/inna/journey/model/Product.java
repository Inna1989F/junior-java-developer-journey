package com.inna.journey.model;

import java.math.BigDecimal;

public class Product {
    private String name;
    private BigDecimal price;

    public Product(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public boolean isCheaperThan(BigDecimal maxPrice){
       return price.compareTo(maxPrice) < 0;
    }
    public String getName() {
        return name;
    }
    public BigDecimal getPrice() {
        return price;
    }
    @Override
    public String toString() {
        return name + " -$" + price;
    }
}
