package com.ecommerce.ecommerse.Controllers;

import com.ecommerce.ecommerse.Models.Product;
import com.ecommerce.ecommerse.Models.Request;
import com.ecommerce.ecommerse.Models.User;
import com.ecommerce.ecommerse.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ecommerce.ecommerse.DTO.LoginRequest;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/users/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        userService.registerUser(user);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/users/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        User user = userService.loginUser(loginRequest);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/Admin/makeUserAdmin")
    public ResponseEntity<?> makeUserAdmin(@RequestBody Request request) {
        userService.makeUserAdmin(request.getId());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/CreateUser")
    public ResponseEntity<?> CreateUser(@RequestBody User user) {
        userService.CreateUser(user);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/UpdateUser")
    public ResponseEntity<?> UpdateUser(@RequestBody User user) {
        userService.UpdateUser(user);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/users")
    public ResponseEntity<?> DeleteUser(@RequestBody User user) {
        userService.DeleteUser(user);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> DeleteUserById(@PathVariable String id) {
        userService.DeleteUserById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user-by-email/{email}")
    public ResponseEntity<?> getUser(@PathVariable String email) {
        User user = userService.GetUserByEmail(email);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers() {
        List<User> users = userService.GetAllUsers();
        return ResponseEntity.ok().body(users);
    }

    @PutMapping("/update-user-address")
    public ResponseEntity<?> UpdateAddress(@RequestBody   Request request) {
        userService.UpdateAddress( request.getId(),request.getAddress() );
        return ResponseEntity.ok().build();
    }

    @GetMapping("/users/address/{id}")
    public ResponseEntity<?> GetAddressById(@PathVariable String id) {
        String Address = userService.GetAddressById(id);
        return ResponseEntity.ok(Address);
    }

    @GetMapping("/user/favorite-products/{id}")
    public ResponseEntity<?> GetFavProducts(@PathVariable String id) {
        Set<Product> products = userService.getFavoriteProducts(id);
        return ResponseEntity.ok().body(products);
    }

}
