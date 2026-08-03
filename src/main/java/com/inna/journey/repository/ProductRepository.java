package com.inna.journey.repository;

import com.inna.journey.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductRepository {
    private List<Product> products = new ArrayList<>();

    public void add(Product product){
        if(containsProduct(product.getName())){
            throw new IllegalArgumentException(
                    "Product with name '" + product.getName() + "' already exists.");
        }
products.add(product);
    }
    public List<Product> getAll(){
        return products;
    }
    private boolean containsProduct(String name) {
        for(Product product: products) {
            if (product.getName().equals(name)) {
                return true;
            }
        }
            return false;
        
    }
    public Optional<Product> findByName(String name){
        for(Product product : products){
            if (product.getName().equals(name)) {
                return Optional.of(product);
            }
        }
        return Optional.empty();
    }
}

