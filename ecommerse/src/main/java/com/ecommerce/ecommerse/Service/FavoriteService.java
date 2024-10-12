package com.ecommerce.ecommerse.Service;

import com.ecommerce.ecommerse.Models.Product;
import com.ecommerce.ecommerse.Models.User;
import com.ecommerce.ecommerse.Repo.ProductRepo;
import com.ecommerce.ecommerse.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FavoriteService {
    @Autowired
    ProductRepo productRepo;

    @Autowired
    UserRepo userRepo;

    public void addProductToFavorite(String UserId ,int productId) {
        User user = userRepo.GetUserById(UserId);
        Product product = productRepo.GetProductById(productId);
        user.getFavoriteProducts().add(product);
        userRepo.save(user);
    }

    public void removeProductFromFavorite(String UserId ,int productId) {
        User user = userRepo.GetUserById(UserId);
        Product product = productRepo.GetProductById(productId);
        user.getFavoriteProducts().remove(product);
        userRepo.save(user);
    }
}
