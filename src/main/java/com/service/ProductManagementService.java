package com.service;

import java.util.HashMap;
import java.util.Map;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;

import com.model.Product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ProductManagementService {

    private final Map<Long, Product> products = new HashMap<>();
    private final AtomicInteger productIdCounter = new AtomicInteger(1);

    public Product addProduct(String name, double price, String description) {
        long productId = productIdCounter.getAndIncrement();
        Product product = new Product(productId, name, price, description, 1);
        products.put(productId, product);
        return product;
    }

    public Product getProduct(long productId) {
        Product product = products.get(productId);
        if (product == null) {
            throw new RuntimeException("Product not found");
        }
        return product;
    }

    public List<Product> getAllProducts() {
        return products.values().stream().collect(Collectors.toList());
    }

    public void updateProduct(long productId, String name, double price, String description, int version) {
        if (!products.containsKey(productId)) {
            throw new RuntimeException("Product not found");
        }
        Product product = products.get(productId);
        if (product.getVersion() != version) {
            throw new OptimisticLockingFailureException("Optimistic locking failure: Product has been modified by another transaction");
        }
        product.setName(name);
        product.setPrice(price);
        product.setDescription(description);
        product.setVersion(version + 1); // Increment version number
    }

    public void deleteProduct(long productId) {
        if (!products.containsKey(productId)) {
            throw new RuntimeException("Product not found");
        }
        products.remove(productId);
    }
}
