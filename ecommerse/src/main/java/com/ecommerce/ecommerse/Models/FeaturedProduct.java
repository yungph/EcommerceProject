package com.ecommerce.ecommerse.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "featured_product")
public class FeaturedProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "is_featured", nullable = false)
    private boolean isFeatured;

    @ManyToOne
    @JoinColumn(name = "product_id",nullable = false)
    private Product product;

    public FeaturedProduct(Product product, boolean is_featured) {
        this.product = product;
        this.isFeatured = is_featured;
    }
}
