package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.model.Product;
import com.service.Order;
import com.service.OrderProcessingService;
import com.service.ProductManagementService;
import com.service.UserAuthenticationService;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final UserAuthenticationService authService;
    private final ProductManagementService productService;
    private final OrderProcessingService orderService;

    @Autowired
    public ApiController(UserAuthenticationService authService, ProductManagementService productService, OrderProcessingService orderService) {
        this.authService = authService;
        this.productService = productService;
        this.orderService = orderService;
    }

    // User Authentication Endpoints

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestParam String email, @RequestParam String password) {
        authService.registerUser(email, password);
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestParam String email, @RequestParam String password) {
        authService.loginUser(email, password);
        return ResponseEntity.ok("User logged in successfully");
    }

    // Product Management Endpoints

    @PostMapping("/products")
    public ResponseEntity<Product> addProduct(@RequestParam String name, @RequestParam double price, @RequestParam String description) {
        Product product = productService.addProduct(name, price, description);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable long productId) {
        Product product = productService.getProduct(productId);
        return ResponseEntity.ok(product);
    }

    // Order Processing Endpoints

    @PostMapping("/orders")
    public ResponseEntity<Order> createOrder(@RequestParam long productId, @RequestParam int quantity) {
        Order order = orderService.createOrder(productId, quantity);
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

    @GetMapping("/orders/{orderId}")
    public ResponseEntity<Order> getOrder(@PathVariable long orderId) {
        Order order = orderService.getOrder(orderId);
        return ResponseEntity.ok(order);
    }
}
