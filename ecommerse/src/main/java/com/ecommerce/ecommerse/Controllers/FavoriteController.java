package com.ecommerce.ecommerse.Controllers;

import com.ecommerce.ecommerse.Models.Request;
import com.ecommerce.ecommerse.Service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FavoriteController {
    @Autowired
    FavoriteService favoriteService;

    @PostMapping("/add-to-favorite")
    public ResponseEntity<?> AddProductToFav(@RequestBody Request request){
        favoriteService.addProductToFavorite(request.getId(),request.getProductId());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/remove-from-favorite")
    public ResponseEntity<?> RemoveProductFromFav(@RequestBody Request request){
        favoriteService.removeProductFromFavorite(request.getId(),request.getProductId());
        return ResponseEntity.ok().build();
    }


}
