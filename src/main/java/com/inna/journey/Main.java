package com.inna.journey;


import com.inna.journey.model.Product;
import com.inna.journey.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        System.out.println("Junior Java Developer Journey started "
        );

        ProductRepository repository = new ProductRepository();
        repository.add(new Product("Laptop", new BigDecimal("999.99")));
        repository.add(new Product("Mouse", new BigDecimal("29.99")));
        repository.add(new Product("Keyboard", new BigDecimal("79.99")));
        repository.add(new Product("Monitor", new BigDecimal("199.99")));
        repository.add(new Product("USB Cable", new BigDecimal("9.99")));
        System.out.println("Cheaper than 100");
        System.out.println(repository.findCheaperThan(new BigDecimal("100")));

        System.out.println("More expensive than 100");
        System.out.println(repository.findExpensiveThan(new BigDecimal("100")));
}
}

