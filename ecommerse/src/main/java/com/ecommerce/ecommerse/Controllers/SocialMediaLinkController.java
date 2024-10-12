package com.ecommerce.ecommerse.Controllers;

import com.ecommerce.ecommerse.Models.SocialMediaLink;
import com.ecommerce.ecommerse.Service.SocialMediaLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SocialMediaLinkController {
    @Autowired
    SocialMediaLinkService Service;

    @PostMapping("/Admin/links")
    public ResponseEntity<?> setLink(@RequestBody SocialMediaLink link) {
        Service.SetSocialMediaLink(link);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/Admin/links")
    public ResponseEntity<?> updateLink(@RequestBody SocialMediaLink link) {
        Service.UpdateSocialMediaLink(link);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/links")
    public ResponseEntity<?> getAllLinks() {
        return ResponseEntity.ok().body(Service.GetAllSocialMediaLink());
    }

    @GetMapping("/GetByPlatformName/{platformname}")
    public ResponseEntity<?> getByPlatformName(@PathVariable String platformname) {
        return ResponseEntity.ok(Service.GetSocialMediaLink(platformname));
    }

    @DeleteMapping("/links")
    public ResponseEntity<?> deleteLink(@RequestBody SocialMediaLink link) {
        Service.DeleteSocialMediaLink(link);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/Admin/DeleteByPlatform/{platformname}")
    public ResponseEntity<?> deleteByPlatform(@PathVariable String platformname) {
        Service.DeleteByPlatform(platformname);
        return ResponseEntity.ok().build();
    }
}
