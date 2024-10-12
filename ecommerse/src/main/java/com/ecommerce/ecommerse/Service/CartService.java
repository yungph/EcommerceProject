package com.ecommerce.ecommerse.Service;

import com.ecommerce.ecommerse.DTO.CartItemRequest;
import com.ecommerce.ecommerse.Models.Cart;
import com.ecommerce.ecommerse.Models.CartItem;
import com.ecommerce.ecommerse.Models.Product;
import com.ecommerce.ecommerse.Repo.CartItemRepo;
import com.ecommerce.ecommerse.Repo.CartRepo;
import com.ecommerce.ecommerse.Repo.ProductRepo;
import com.ecommerce.ecommerse.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class CartService {

    @Autowired
    private CartRepo cartRepository;

    @Autowired
    private CartItemRepo cartItemRepository;

    private final AtomicLong cartIdGenerator = new AtomicLong(0);



    public Cart getCart(Long id) {
        Cart cart = cartRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Cart not found"));
        BigDecimal totalAmount = cart.getTotalAmount();
        cart.setTotalAmount(totalAmount);
        return cart;
    }

    public void clearCart(Long id) {
        Cart cart = getCart(id);
        cartItemRepository.deleteAllByCartId(id);
        cart.getItems().clear();
        cartRepository.deleteById(id);
    }

    public BigDecimal getTotalPrice(Long id) {
        Cart cart = getCart(id);
        return cart.getTotalAmount();
    }

    public Long initializeNewCart() {
        Cart newCart = new Cart();
        Long newCartId = cartIdGenerator.incrementAndGet();
        newCart.setId(newCartId);
        return cartRepository.save(newCart).getId();
    }
}
