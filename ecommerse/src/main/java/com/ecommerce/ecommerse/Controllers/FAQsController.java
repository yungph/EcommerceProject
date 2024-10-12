package com.ecommerce.ecommerse.Controllers;

import com.ecommerce.ecommerse.Models.FAQs;
import com.ecommerce.ecommerse.Service.FAQsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class FAQsController {
    @Autowired
    private FAQsService faqsService;

    @PostMapping("/Admin/faqs")
    public ResponseEntity<?> createFaq(@RequestBody FAQs faq){
        faqsService.createFAQ(faq);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/Admin/faqs")
    public ResponseEntity<?> updateFaq(@RequestBody FAQs faq){
        faqsService.updateFAQ(faq);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/faqs")
    public ResponseEntity<?> getAllFaq(){
        return ResponseEntity.ok().body(faqsService.getAllFAQs());
    }
    @GetMapping("/faqs/{id}")
    public ResponseEntity<?> getFaqById(@PathVariable int id){
        return ResponseEntity.ok().body(faqsService.getFAQById(id));
    }
    @DeleteMapping("/Admin/faqs/{id}")
    public ResponseEntity<?> deleteFaq(@PathVariable int id){
        faqsService.deleteFAQ(id);
        return ResponseEntity.ok().build();
    }

}
