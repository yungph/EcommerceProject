package com.ecommerce.ecommerse.Service;

import com.ecommerce.ecommerse.Models.Cart;
import com.ecommerce.ecommerse.Models.CartItem;
import com.ecommerce.ecommerse.Models.Product;
import com.ecommerce.ecommerse.Repo.CartItemRepo;
import com.ecommerce.ecommerse.Repo.CartRepo;
import com.ecommerce.ecommerse.Repo.ProductRepo;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartItemService {
    @Autowired
    CartItemRepo cartItemRepo;

    @Autowired
    ProductService productService ;

    @Autowired
    CartService cartService;
    @Autowired
    private CartRepo cartRepo;

//    public void addItem(Long cartId,int productId,int quantity) {
//        Cart cart = cartService.getCart(cartId);
//        Product product = productService.getProductById(productId);
//        CartItem cartItem = cart.getItems()
//                .stream()
//                .filter(item -> item.getProduct().getId() == productId)
//                .findFirst().orElse(new CartItem());
//        if (cartItem.getId() == null) {
//            cartItem.setCart(cart);
//            cartItem.setProduct(product);
//            cartItem.setQuantity(quantity);
//            cartItem.setUnitPrice(product.getPrice());
//        }
//        else{
//            cartItem.setQuantity(cartItem.getQuantity() + quantity);
//        }
//        cartItem.setTotalPrice();
//        cart.addItem(cartItem);
//        cartItemRepo.save(cartItem);
//        cartRepo.save(cart);
//    }
public void addItem(Long cartId, int productId, int quantity) {
    Cart cart = cartService.getCart(cartId);
    Product product = productService.getProductById(productId);
    CartItem cartItem = cart.getItems()
            .stream()
            .filter(item -> item.getProduct().getId() == productId)
            .findFirst()
            .orElse(new CartItem());

    if (cartItem.getId() == null) {
        cartItem.setCart(cart);
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);
        cartItem.setUnitPrice(product.getPrice());
    } else {
        cartItem.setQuantity(cartItem.getQuantity() + quantity);
    }

    cartItem.setTotalPrice();
    cart.addItem(cartItem);
    cartItemRepo.save(cartItem);

    // Calculate and update the total amount for the cart
    calculateTotalAmount(cart);
}






    public void removeItem(Long cartId,int productId) {
        Cart cart = cartService.getCart(cartId);
        CartItem itemToRemove = getCartItem(cartId,productId);
        cart.removeItem(itemToRemove);
        cartRepo.save(cart);
    }

    public void updateItemQuantity(Long cartId,int productId,int quantity) {
        Cart cart = cartService.getCart(cartId);
        cart.getItems()
                .stream()
                .filter(item -> item.getProduct().getId() == productId)
                .findFirst()
                .ifPresent(item -> {
                    item.setQuantity(quantity);
                    item.setUnitPrice(item.getProduct().getPrice());
                    item.setTotalPrice();
                });
        BigDecimal totalAmount = cart.getTotalAmount();
        cart.setTotalAmount(totalAmount);
        cartRepo.save(cart);
    }

    public CartItem getCartItem(Long cartId , int productId) {
        Cart cart = cartService.getCart(cartId);
        return cart.getItems()
                .stream()
                .filter(item -> item.getProduct().getId() == productId)
                .findFirst().orElseThrow(()->new ResourceNotFoundException("item Not Found"));
    }

    public void calculateTotalAmount(Cart cart) {
        BigDecimal total = cart.getItems().stream()
                .map(CartItem::getTotalPrice)  // get the total price of each CartItem
                .reduce(BigDecimal.ZERO, BigDecimal::add);  // sum up all total prices

        cart.setTotalAmount(total);  // set the calculated total amount in the cart
        cartRepo.save(cart);  // save the updated cart with the new total amount
    }


}
