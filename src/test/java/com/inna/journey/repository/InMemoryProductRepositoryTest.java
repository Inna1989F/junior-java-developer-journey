package com.inna.journey.repository;
import com.inna.journey.model.Product;
import com.inna.journey.service.ProductService;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InMemoryProductRepositoryTest {
    @Test
    void add_validProduct_productAdded() {
        ProductRepository repository = new InMemoryProductRepository();
        Product mouse = new Product(
                "Mouse", new BigDecimal("29.99"));

        repository.add(mouse);

        assertEquals(1, repository.getAll().size());
        assertEquals(mouse, repository.getAll().get(0));
    }

    @Test
    void findByName_existingProduct_productReturned() {
        ProductRepository repository = new InMemoryProductRepository();
        Product mouse =
                new Product("Mouse", new BigDecimal("29.99"));

        repository.add(mouse);

        Optional<Product> result = repository.findByName("Mouse");

        assertTrue(result.isPresent());
        assertEquals(mouse, result.get());
    }

    @Test
    void add_duplicateName_notOk() {
        ProductRepository repository = new InMemoryProductRepository();

        Product firstMouse =
                new Product("Mouse", new BigDecimal("29.99"));

        Product secondMouse =
                new Product("Mouse", new BigDecimal("39.99"));

        repository.add(firstMouse);

        assertThrows(
                IllegalArgumentException.class,
                () -> repository.add(secondMouse)
        );
    }

    @Test
    void findByName_existingProductReturned() {
        ProductRepository repository = new InMemoryProductRepository();
        Product mouse =
                new Product("Mouse", new BigDecimal("29.99"));
        repository.add(mouse);
        Optional<Product> result = repository.findByName("Mouse");
        assertTrue(result.isPresent());
        assertEquals(mouse, result.get());

    }

    @Test
    void findByName_nonExistingProduct_emptyOptionalReturned() {
        ProductRepository repository = new InMemoryProductRepository();

        Optional<Product> result = repository.findByName("iphone");

        assertTrue(result.isEmpty());
    }
}
