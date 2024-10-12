package com.ecommerce.ecommerse.Service;

import com.ecommerce.ecommerse.Models.Category;
import com.ecommerce.ecommerse.Repo.CategoryRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepo categoryRepo;

    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }

    public Category getCategoryById(int id) {
        return categoryRepo.findById(id).get();
    }
    public void saveCategory(Category category) {
        categoryRepo.save(category);
    }
    public void updateCategory(Category category) {
        Optional<Category> optionalCategory = categoryRepo.findById(category.getId());
        if (optionalCategory.isPresent()) {
            Category existingCategory = optionalCategory.get();
            existingCategory.setName(category.getName());
            // Update other fields if necessary
            categoryRepo.save(existingCategory);
        } else {
            throw new EntityNotFoundException("Category not found with id " + category.getId());
        }
    }



    public void deleteCategoryById(int id) {
        categoryRepo.deleteById(id);
    }

    public Page<Category> getAllCategoriesPaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return categoryRepo.findAll(pageable);
    }

}
