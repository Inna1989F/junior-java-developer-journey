package com.inna.journey.repository;

import com.inna.journey.model.Product;
import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    void add(Product product);

    List<Product> getAll();

    Optional<Product> findByName(String name);

}

