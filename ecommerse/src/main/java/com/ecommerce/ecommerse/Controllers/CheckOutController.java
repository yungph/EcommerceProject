package com.ecommerce.ecommerse.Controllers;

import com.ecommerce.ecommerse.DTO.CheckOutRequest;
import com.ecommerce.ecommerse.Models.Order;
import com.ecommerce.ecommerse.Service.CheckOutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/checkout")
public class CheckOutController {

    @Autowired
    CheckOutService checkOutService;

    @PostMapping
    public ResponseEntity<Order> checkout(@RequestBody CheckOutRequest request) {
        Order processedOrder = checkOutService.checkout(request);
        return ResponseEntity.ok(processedOrder);
    }
}
