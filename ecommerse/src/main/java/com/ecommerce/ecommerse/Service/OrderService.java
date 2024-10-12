package com.ecommerce.ecommerse.Service;

import com.ecommerce.ecommerse.Models.Order;
import com.ecommerce.ecommerse.Models.OrderStatus;
import com.ecommerce.ecommerse.Models.Product;
import com.ecommerce.ecommerse.Repo.OrderRepo;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private ProductService productService;
    @Autowired
    private OrderRepo orderRepository;
    @Autowired
    private TwilioSmsService twilioSmsService;



    public Order createOrder( String userId,
                              int productId,
                              int quantity) {

        Product product = productService.getProductById(productId);
        if (product == null) {
            return null; // or handle the error appropriately
        }

        // Create a new Order
        Order order = new Order();
        order.setUserId(userId);
        order.setProduct(product);
        order.setQuantity(quantity);
        order.setCreatedAt(LocalDateTime.now());
        order.calculateTotalPrice(); // Automatically calculate total price

        // Save the order
        Order savedOrder = orderRepository.save(order);
        twilioSmsService.sendSmsToSeller(savedOrder);
        if(CheckIfProductExist(productId)){
            updateQuantity(quantity,productId);
            return savedOrder;
        }
        else
            return null;

    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order updateOrderStatus(Long orderId, OrderStatus status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        order.setStatus(status);
        return orderRepository.save(order);
    }

    public List<Object[]> getMostSoldProducts() {
        return orderRepository.findMostSoldProducts();
    }

    public List<Object[]> getLeastSoldProducts() {
        return orderRepository.findLeastSoldProducts();
    }

    public List<Object[]> getBestClients() {
        return orderRepository.findBestClients();
    }

    public BigDecimal getAvgCartPrice() {
        return orderRepository.findAvgCartPrice();
    }
    private void updateQuantity(int quantity ,int id) {
        orderRepository.updateQuantity(quantity, id);
    }
    private boolean CheckIfProductExist(int id){
        int quantity = orderRepository.checkQuantity(id);
        return quantity > 0;
    }
}

