package com.inna.journey.service;

import com.inna.journey.model.Product;
import com.inna.journey.repository.InMemoryProductRepository;
import com.inna.journey.repository.ProductRepository;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductServiceTest {
    @Test
    void findCheaperThan_productsExist_matchingProductsReturned() {
        ProductRepository repository = new InMemoryProductRepository();
        ProductService service = new ProductService(repository);

        Product mouse = new Product("Mouse", new BigDecimal("29.99"));
        Product keyboard = new Product("Keyboard", new BigDecimal("59.99"));
        Product monitor = new Product("Monitor", new BigDecimal("199.99"));

        repository.add(mouse);
        repository.add(keyboard);
        repository.add(monitor);
        List<Product> result = service.findCheaperThan(new BigDecimal("30.00"));
        assertEquals(1,result.size());
        assertEquals(mouse, result.get(0));

    }
}
