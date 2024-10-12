package com.ecommerce.ecommerse.Controllers;

import com.ecommerce.ecommerse.Models.Request;
import com.ecommerce.ecommerse.Service.FeaturedProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/featured-products")
public class FeaturedProductController {
    @Autowired
    private FeaturedProductService service;

    @GetMapping("/get-featured-products")
    public ResponseEntity<?> GetFeaturedProducts() {
        return ResponseEntity.ok(service.getAllFeaturedProducts());
    }

    @PostMapping("/add-featured-product")
    public ResponseEntity<?> addFeaturedProduct(@RequestBody Request request){
        service.addFeaturedProduct(request.getProductId());
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update-featured-product/{id}")
    public ResponseEntity<?> updateFeaturedProduct(@PathVariable int id, @RequestBody Request request){
        service.updateFeaturedProduct(id,request.getIs_featured());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete-featured-product/{id}")
    public ResponseEntity<?> deleteFeaturedProduct(@PathVariable int id){
        service.deleteFeaturedProduct(id);
        return ResponseEntity.ok().build();
    }

}
