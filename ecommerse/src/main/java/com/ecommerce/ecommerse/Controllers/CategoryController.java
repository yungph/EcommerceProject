package com.ecommerce.ecommerse.Controllers;

import com.ecommerce.ecommerse.Models.Category;
import com.ecommerce.ecommerse.Models.Product;
import com.ecommerce.ecommerse.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/Admin/categories")
    public ResponseEntity<?> CreateCategory(@RequestBody Category category) {
        categoryService.saveCategory(category);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/Admin/categories")
    public ResponseEntity<?> UpdateCategory(@RequestBody Category category) {
        categoryService.updateCategory(category);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/categories")
    public ResponseEntity<?> GetAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable int id) {
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    @DeleteMapping("/Admin/categories/{id}")
    public ResponseEntity<?> DeleteCategory(@PathVariable int id) {
        categoryService.deleteCategoryById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/categoriesPaginated")
    public ResponseEntity<?> getAllCategoriesPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size){
        Page<Category> categories = categoryService.getAllCategoriesPaginated(page, size);
        return ResponseEntity.ok(categories);
    }



}
