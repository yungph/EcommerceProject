package com.ecommerce.ecommerse.Controllers;

import com.ecommerce.ecommerse.Models.Cart;
import com.ecommerce.ecommerse.Models.CartItem;
import com.ecommerce.ecommerse.Models.Product;
import com.ecommerce.ecommerse.Models.Request;
import com.ecommerce.ecommerse.Service.CartItemService;
import com.ecommerce.ecommerse.Service.CartService;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
public class CartController {

    @Autowired
    CartService cartService;

    @Autowired
    CartItemService cartItemService;

    @GetMapping("/{cartId}/getcart")
    public ResponseEntity<?> getCart(@PathVariable Long cartId){
        try {
            Cart cart = cartService.getCart(cartId);
            return ResponseEntity.ok(cart);
        } catch (ResourceNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/{cartId}/clear")
    public ResponseEntity<?> clearCart(@PathVariable Long cartId){
        cartService.clearCart(cartId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{cartId}/cart/total-price")
    public ResponseEntity<?> getTotalAmount(@PathVariable Long cartId){
        BigDecimal totalPrice = cartService.getTotalPrice(cartId);
        return ResponseEntity.ok(totalPrice);
    }

}



