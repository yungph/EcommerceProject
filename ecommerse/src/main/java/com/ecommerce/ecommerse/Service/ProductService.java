package com.ecommerce.ecommerse.Service;

import com.ecommerce.ecommerse.Models.Product;
import com.ecommerce.ecommerse.Repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepo productRepo;

    public void AddProduct(Product product) {
        productRepo.save(product);
    }

    public void updateProduct(Product product) {
        productRepo.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    public Product getProductById(int id) {
        return productRepo.findById(id).get();
    }

    public void deleteProduct(int id) {
        productRepo.SoftDelete(id);
    }

    public List<Product> getDeletedProducts() {
        return productRepo.findIsDeleted();
    }

    public Page<Product> getAllProductsPaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productRepo.findAll(pageable);
    }

    public List<Product> GetProductByCategoryName(String categoryName) {
        return productRepo.findAllByCategoryName(categoryName);
    }

    public Product FindByName(String productName) {
        return productRepo.findByName(productName);
    }


}




