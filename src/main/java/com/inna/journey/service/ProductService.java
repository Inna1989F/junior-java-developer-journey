package com.inna.journey.service;

import com.inna.journey.model.Product;
import com.inna.journey.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductService {
    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }
    public List<Product> findCheaperThan(BigDecimal maxPrice) {
        List<Product> result = new ArrayList<>();

        for(Product product : repository.getAll()) {
            if(product.getPrice().compareTo(maxPrice) < 0) {
                result.add(product);
            }
        }
        return result;
    }
    public List<Product> findExpensiveThan(BigDecimal minPrice) {
        List<Product> result1 = new ArrayList<>();
        for(Product product: repository.getAll()) {
            if(product.getPrice().compareTo(minPrice) > 0) {
                result1.add(product);
            }
        }
        return result1;
    }
}
