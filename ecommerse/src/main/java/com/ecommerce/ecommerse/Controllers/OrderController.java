package com.ecommerce.ecommerse.Controllers;

import com.ecommerce.ecommerse.Models.Order;
import com.ecommerce.ecommerse.Models.OrderStatus;
import com.ecommerce.ecommerse.Repo.OrderRepo;
import com.ecommerce.ecommerse.Service.OrderService;
import com.ecommerce.ecommerse.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
//@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderRepo orderRepository;

    @PostMapping("/orders")
    public ResponseEntity<Order> createOrder(@RequestParam String userId,
                                             @RequestParam int productId,
                                             @RequestParam int quantity) {
        Order savedOrder = orderService.createOrder(userId,productId,quantity);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getAllOrders() {
        return new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);
    }

    @PutMapping("/orders/{orderId}/status")
    public ResponseEntity<Order> updateOrderStatus(@PathVariable Long orderId, @RequestParam OrderStatus status) {
        return new ResponseEntity<>(orderService.updateOrderStatus(orderId, status), HttpStatus.OK);
    }

    @GetMapping("/orders/most-sold-products")
    public ResponseEntity<List<Object[]>> getMostSoldProducts() {
        return new ResponseEntity<>(orderService.getMostSoldProducts(), HttpStatus.OK);
    }

    @GetMapping("/orders/least-sold-products")
    public ResponseEntity<List<Object[]>> getLeastSoldProducts() {
        return new ResponseEntity<>(orderService.getLeastSoldProducts(), HttpStatus.OK);
    }

    @GetMapping("/orders/best-clients")
    public ResponseEntity<List<Object[]>> getBestClients() {
        return new ResponseEntity<>(orderService.getBestClients(), HttpStatus.OK);
    }

    @GetMapping("/orders/avg-cart-price")
    public ResponseEntity<BigDecimal> getAvgCartPrice() {
        return new ResponseEntity<>(orderService.getAvgCartPrice(), HttpStatus.OK);
    }
}

