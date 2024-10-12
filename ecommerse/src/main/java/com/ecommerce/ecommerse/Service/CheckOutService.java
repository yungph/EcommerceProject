package com.ecommerce.ecommerse.Service;

import com.ecommerce.ecommerse.DTO.CheckOutRequest;
import com.ecommerce.ecommerse.Models.*;
import com.ecommerce.ecommerse.Repo.CartRepo;
import com.ecommerce.ecommerse.Repo.OrderRepo;
import com.ecommerce.ecommerse.Repo.ProductRepo;
import com.ecommerce.ecommerse.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Service
public class CheckOutService {

    @Autowired
    private CartRepo cartRepository;
    @Autowired
    private OrderRepo orderRepository;
    @Autowired
    private ProductService productService;

    public Order checkout(CheckOutRequest request) {
        // Fetch the cart by cartId
        Cart cart = cartRepository.findById(request.getCartId())
                .orElseThrow(() -> new IllegalArgumentException("Cart not found"));

        // Create an order for each cart item
        BigDecimal totalOrderAmount = BigDecimal.ZERO;
        Order order = new Order();
        for (CartItem cartItem : cart.getItems()) {
            order.setUserId(request.getUserId());
            order.setProduct(cartItem.getProduct());
            order.setQuantity(cartItem.getQuantity());
            order.setTotalPrice(cartItem.getTotalPrice());
            order.setCreatedAt(LocalDateTime.now());
            order.setStatus(OrderStatus.PENDING); // Default order status

            orderRepository.save(order);
            totalOrderAmount = totalOrderAmount.add(cartItem.getTotalPrice());
        }

        // After checkout, you may want to clear the cart or update its status
//        cart.getItems().clear();
        cartRepository.save(cart);

        // Return confirmation or the final order (or list of orders)
        return order; // Modify as needed to return the relevant order details
    }
}