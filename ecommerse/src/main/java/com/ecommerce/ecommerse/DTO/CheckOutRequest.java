package com.ecommerce.ecommerse.DTO;

import com.ecommerce.ecommerse.Models.CartItem;
import com.ecommerce.ecommerse.Models.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CheckOutRequest {
    private String userId;
    private Long cartId;
    private String paymentMethod;
}
