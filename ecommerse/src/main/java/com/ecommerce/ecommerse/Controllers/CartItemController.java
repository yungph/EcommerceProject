package com.ecommerce.ecommerse.Controllers;

import com.ecommerce.ecommerse.Service.CartItemService;
import com.ecommerce.ecommerse.Service.CartService;
import com.ecommerce.ecommerse.exception.CartNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CartItemController {
    @Autowired
    CartItemService cartItemService;

    @Autowired
    CartService cartService;

//    @PostMapping("/item/add")
//    public ResponseEntity<?> addItemToCart(@RequestParam(required = false) Long cartId,
//                                           @RequestParam int productId,
//                                           @RequestParam int quantity) {
//        try {
//            if (cartId == null) {
//                cartId = cartService.initializeNewCart();
//            }
//            cartItemService.addItem(cartId, productId, quantity);
//            return ResponseEntity.ok().build();
//
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }

    @PostMapping("/item/add")
    public ResponseEntity<?> addItemToCart(@RequestParam(required = false) Long cartId,
                                           @RequestParam int productId,
                                           @RequestParam int quantity) {
        try {
            // If cartId is null, create a new cart
            if (cartId == null) {
                cartId = cartService.initializeNewCart();
            }

            // Add the item to the cart
            cartItemService.addItem(cartId, productId, quantity);
            return ResponseEntity.ok().build();

        } catch (CartNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @DeleteMapping("/cart/{cartId}/item/{productId}/remove")
    public ResponseEntity<?> removeItemFromCart(@PathVariable Long cartId,@PathVariable int productId) {
        cartItemService.removeItem(cartId,productId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/cart/{cartId}/item/{productId}/update")
    public ResponseEntity<?> updateItemQuantity(@PathVariable Long cartId,
                                                @PathVariable int productId,
                                                @RequestParam int quantity) {
        cartItemService.updateItemQuantity(cartId, productId, quantity);
        return ResponseEntity.ok().build();

    }
}
