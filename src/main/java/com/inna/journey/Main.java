package com.inna.journey;


import com.inna.journey.model.Product;
import com.inna.journey.repository.ProductRepository;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        System.out.println("Junior Java Developer Journey started "
        );

        ProductRepository repository = new ProductRepository();
        Product keyboard = new Product(
                "Keyboard", new BigDecimal("79.99")
        );

        System.out.println(keyboard.getName());
        System.out.println(keyboard.getPrice());
        System.out.println(keyboard.isCheaperThan(new BigDecimal("100")));
        }
    }
