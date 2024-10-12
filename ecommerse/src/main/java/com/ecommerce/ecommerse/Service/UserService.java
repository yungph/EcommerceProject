package com.ecommerce.ecommerse.Service;

import com.ecommerce.ecommerse.DTO.LoginRequest;
import com.ecommerce.ecommerse.Models.Product;
import com.ecommerce.ecommerse.Models.User;
import com.ecommerce.ecommerse.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    public String hashPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    @Transactional
    public void registerUser(User user) {
        String id = UUID.randomUUID().toString().replace("-", "");
        user.setId(id);
        String pass=hashPassword(user.getPassword());
        user.setPassword(pass);
        userRepo.save(user);
        userRepo.makeUserRole(user.getId());
    }
//
    public User loginUser(LoginRequest loginRequest) {
        User user = userRepo.findByEmail(loginRequest.getEmail());
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        if (!new BCryptPasswordEncoder().matches(loginRequest.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Invalid credentials");
        }

        return user; // Return user or a token depending on your implementation
    }
//
    public void CreateUser(User user) {
        String id = UUID.randomUUID().toString().replace("-", "");
        user.setId(id);
        userRepo.save(user);
    }

    public void makeUserAdmin(String id) {
        userRepo.makeUserRoleAdmin(id);
    }
    public void UpdateUser(User user) {
        userRepo.save(user);
    }

    public void DeleteUser(User user) {
        userRepo.delete(user);
    }
    public void DeleteUserById(String id) {
        userRepo.deleteById(id);
    }

    public User GetUserByEmail(String email) {
        return userRepo.GetUserByEmail(email);
    }
    public User GetUserById(String id) {
        return userRepo.findById(id).get();
    }

    public List<User> GetAllUsers() {
        return userRepo.findAll();
    }

    @Transactional
    public void UpdateAddress(String id, String address) {
        userRepo.updateAddress(id, address);
    }
    public String GetAddressById(String id) {
        return GetUserById(id).getAddress();
    }

//    public Set<Product> GetFavProducts(String id){
//        return userRepo.GetFavProducts(id);
//    }

    public Set<Product> getFavoriteProducts(String userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        return user.getFavoriteProducts();  // Returns the set of favorite products
    }










}
