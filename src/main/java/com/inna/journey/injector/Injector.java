package com.inna.journey.injector;

import com.inna.journey.annotation.Component;
import com.inna.journey.repository.InMemoryProductRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Injector {
    private static final Logger logger =
            LogManager.getLogger(Injector.class);
    private static final Injector injector = new Injector();

    public static Injector getInjector() {
        return injector;
    }

    public Object getInstance(Class<?> interfaceClazz) {
        Class<?> implementationClazz = InMemoryProductRepository.class;
        if (!interfaceClazz.isAssignableFrom(implementationClazz)) {
            throw new RuntimeException("Implementation doesn't match interface ");
        }
        if (!implementationClazz.isAnnotationPresent(Component.class)) {
            throw new RuntimeException("Class doesn't have @Component");
        }
        try {
            Object instance = implementationClazz
                    .getDeclaredConstructor()
                    .newInstance();
            return instance;
        } catch (Exception e) {
            throw new RuntimeException(
                    "Can't create instance of " + implementationClazz.getName(),
                    e);
        }
    }
}
