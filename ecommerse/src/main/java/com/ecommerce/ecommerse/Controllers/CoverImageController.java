package com.ecommerce.ecommerse.Controllers;

import com.ecommerce.ecommerse.Models.CoverImage;
import com.ecommerce.ecommerse.Service.CoverImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/Admin")
public class CoverImageController {
    @Autowired
    CoverImageService coverImageService;

    @PostMapping("/coverimages")
    public ResponseEntity<?> SetCoverImage(@RequestBody CoverImage coverImage) {
        coverImageService.setCoverImage(coverImage);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/coverimages")
    public ResponseEntity<?> UpdateCoverImage(@RequestBody CoverImage coverImage) {
        coverImageService.UpdateCoverImage(coverImage);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/coverimages")
    public ResponseEntity<?> GetAllCoverImage() {
        return ResponseEntity.ok(coverImageService.getCoverImages());
    }

    @GetMapping("/coverimages/{id}")
    public ResponseEntity<?> GetCoverImageById(@PathVariable int id) {
        return ResponseEntity.ok(coverImageService.getCoverImage(id));
    }

    @DeleteMapping("/coverimages/{id}")
    public ResponseEntity<?> DeleteCoverImage(@PathVariable int id) {
        coverImageService.deleteCoverImage(id);
        return ResponseEntity.ok().build();
    }

}
