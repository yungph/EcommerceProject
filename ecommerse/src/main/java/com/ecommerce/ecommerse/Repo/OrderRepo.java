package com.ecommerce.ecommerse.Repo;

import com.ecommerce.ecommerse.Models.Order;
import com.ecommerce.ecommerse.Models.OrderStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {
    List<Order> findByStatus(OrderStatus status);

    @Query("SELECT o.product.id, COUNT(o.product.id) FROM Order o GROUP BY o.product.id ORDER BY COUNT(o.product.id) DESC")
    List<Object[]> findMostSoldProducts();

    @Query("SELECT o.product.id, COUNT(o.product.id) FROM Order o GROUP BY o.product.id ORDER BY COUNT(o.product.id) ASC")
    List<Object[]> findLeastSoldProducts();

    @Query("SELECT o.userId, COUNT(o.userId) FROM Order o GROUP BY o.userId ORDER BY COUNT(o.userId) DESC")
    List<Object[]> findBestClients();

    @Query("SELECT AVG(o.totalPrice) FROM Order o")
    BigDecimal findAvgCartPrice();

    @Modifying
    @Transactional
    @Query(value = "update product set quantity = quantity-:qu where id=:id",nativeQuery = true)
    void updateQuantity(@Param("qu") int qu, @Param("id") int id);

    @Query(value = "select quantity from product where id=:id",nativeQuery = true)
    int checkQuantity(@Param("id") int id);
}

