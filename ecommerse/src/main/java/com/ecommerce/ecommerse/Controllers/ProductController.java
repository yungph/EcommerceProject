package com.ecommerce.ecommerse.Controllers;

import com.ecommerce.ecommerse.Models.Product;
import com.ecommerce.ecommerse.Models.Request;
import com.ecommerce.ecommerse.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/Admin/products")
    public ResponseEntity<?> AddProduct(@RequestBody Product product) {
        productService.AddProduct(product);
        return ResponseEntity.ok(true);
    }

    @PutMapping("/Admin/products")
    public ResponseEntity<?> UpdateProduct(@RequestBody Product product) {
        productService.updateProduct(product);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/products")
    public ResponseEntity<?> GetAllProduct() {
        return ResponseEntity.ok().body(productService.getAllProducts());
    }

    @GetMapping("/products/paginated")
    public ResponseEntity<?> getAllProductsPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size){
                Page<Product> products = productService.getAllProductsPaginated(page, size);
                return ResponseEntity.ok(products);
        }

    @GetMapping("/products/{id}")
    public ResponseEntity<?> GetProductById(@PathVariable int id) {
        return ResponseEntity.ok().body(productService.getProductById(id));
    }

    @GetMapping("/Admin/GetDeletedProducts")
    public ResponseEntity<?> GetDeletedProducts() {
        return ResponseEntity.ok().body(productService.getDeletedProducts());
    }

    @GetMapping("/products/category/{name}")
    public ResponseEntity<?> getProductsByCategoryName(@PathVariable String name) {
        List<Product> products = productService.GetProductByCategoryName(name);
        if (products.isEmpty()) {
            return ResponseEntity.status(404).body(null); // Category or products not found
        }
        return ResponseEntity.ok(products);
    }

    @DeleteMapping("/Admin/products/{id}")
    public ResponseEntity<?> DeleteProduct(@PathVariable int id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/products/search/{productname}")
    public ResponseEntity<?> SearchProduct(@PathVariable String productname) {
        return ResponseEntity.ok().body(productService.FindByName(productname));
    }






}
