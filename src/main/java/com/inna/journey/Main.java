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
        Product product = new Product(
                "Keyboard", new BigDecimal("79.99")
        );
        Product product1 = new Product("Coffee", new BigDecimal("12.99"));
repository.add(product);
repository.add(product1);
        List<Product> products = repository.getAll();
Optional<Product> result = repository.findByName("Key");
if(result.isPresent()) {
    System.out.println(result);
}else{
    System.out.println("This product not found.");
}

        System.out.println(products);
        System.out.println(product);
        System.out.println(product.getPrice());
        System.out.println(product1.isCheaperThan(new BigDecimal("100")));
        }
    }
