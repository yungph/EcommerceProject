package com.ecommerce.ecommerse.Service;

import com.ecommerce.ecommerse.Models.FeaturedProduct;
import com.ecommerce.ecommerse.Models.Product;
import com.ecommerce.ecommerse.Repo.FeaturedProductRepo;
import com.ecommerce.ecommerse.Repo.ProductRepo;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeaturedProductService {
    @Autowired
    FeaturedProductRepo featuredProductRepository;

    @Autowired
    ProductRepo productRepository;

    public List<FeaturedProduct> getAllFeaturedProducts() {
        return featuredProductRepository.findFeaturedProductByIsFeatured();
    }

    public void addFeaturedProduct(int productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        FeaturedProduct featuredProduct = new FeaturedProduct(product, true);
        featuredProductRepository.save(featuredProduct);
    }

    public void updateFeaturedProduct(int id, boolean isFeatured) {
        FeaturedProduct featuredProduct = featuredProductRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Featured Product not found"));

        featuredProduct.setFeatured(isFeatured);
        featuredProductRepository.save(featuredProduct);
    }

    public void deleteFeaturedProduct(int id) {
        featuredProductRepository.deleteById(id);

    }

}
