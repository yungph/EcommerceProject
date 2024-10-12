package com.ecommerce.ecommerse.Repo;

import com.ecommerce.ecommerse.Models.FeaturedProduct;
import com.ecommerce.ecommerse.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FeaturedProductRepo extends JpaRepository<FeaturedProduct, Integer> {

    @Query(value = "select * from featured_product where is_featured = true ",nativeQuery = true)
    List<FeaturedProduct> findFeaturedProductByIsFeatured();

    Optional<FeaturedProduct> findByProduct(Product product);
}
