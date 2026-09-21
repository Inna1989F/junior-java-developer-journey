package com.inna.journey;


import com.inna.journey.model.Product;
import com.inna.journey.repository.InMemoryProductRepository;
import com.inna.journey.repository.ProductRepository;
import com.inna.journey.service.ProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.List;

public class Main {
    private static final Logger logger =
            LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        logger.error("TEST FILE LOGGER");
        logger.debug("This is DEBUG");
        logger.info("Application started");
        logger.warn("This is WARN");
        logger.error("This is ERROR");

        ProductRepository repository = new InMemoryProductRepository();
        ProductService service = new ProductService(repository); //constructor dependency injection

        repository.add(new Product("Laptop", new BigDecimal("999.99")));
        repository.add(new Product("Mouse", new BigDecimal("29.99")));
        repository.add(new Product("Keyboard", new BigDecimal("79.99")));
        repository.add(new Product("Monitor", new BigDecimal("199.99")));
        repository.add(new Product("USB Cable", new BigDecimal("9.99")));

        List<Product> cheapProducts = service.findCheaperThan(new BigDecimal("100"));

        List<Product> expensiveProducts = service.findExpensiveThan(new BigDecimal("100"));
        System.out.println(cheapProducts);
        System.out.println(expensiveProducts);
}
}

