package com.ecommerce.ecommerse.Repo;

import com.ecommerce.ecommerse.Models.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepo extends JpaRepository<CartItem, Long> {
    @Query(value = "select * from cart_item where cart_id=:id and product_id= :productId " ,nativeQuery = true)
    CartItem findByCartIdAndProductId(@Param("id") int id, @Param("productId") int productId);

    void deleteAllByCartId(Long id);


}
