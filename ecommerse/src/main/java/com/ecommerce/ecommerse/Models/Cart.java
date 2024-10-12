package com.ecommerce.ecommerse.Models;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal totalAmount=BigDecimal.ZERO;

    @OneToMany(mappedBy = "cart",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<CartItem> items=new ArrayList<>();

    // Constructors, Getters, and Setters
    public Cart() {}

    public Cart(List<CartItem> items) {
        this.items = items;
    }



    public BigDecimal getTotalAmount() {
        return items.stream()
                .map(CartItem::getTotalPrice) // Get totalPrice from each CartItem
                .reduce(BigDecimal.ZERO, BigDecimal::add); // Sum them all
    }


    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public void addItem(CartItem cartItem) {
        if (items == null) {
            items = new ArrayList<>(); // Ensure the list is initialized if it was set to null
        }
        items.add(cartItem);
    }


    public void removeItem(CartItem item) {
        this.items.remove(item);
    }


//    private void updateTotalAmount() {
//        this.totalAmount=
//    }
}
