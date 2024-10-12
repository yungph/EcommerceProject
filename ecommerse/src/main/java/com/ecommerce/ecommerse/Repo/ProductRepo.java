package com.ecommerce.ecommerse.Repo;

import com.ecommerce.ecommerse.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {

    @Query(value = "Select * from Product where is_deleted=0" ,nativeQuery = true)
    List<Product> findAll();

    @Modifying
    @Transactional
    @Query(value = "update Product set is_deleted=1 where id=:id" , nativeQuery = true)
    void SoftDelete(@Param("id") int id);

    @Query(value = "Select * from Product where is_deleted=1 " , nativeQuery = true)
    List<Product> findIsDeleted();

//    @Query(value = "SELECT DISTINCT p.* FROM Product p WHERE p.category_id = (SELECT id FROM Category WHERE name = :categoryName) AND p.is_deleted = false", nativeQuery = true)
//    List<Product> findAllByCategoryName(@Param("categoryName") String categoryName);

    @Query(value = "SELECT p.* FROM Product p JOIN Category c ON p.category_id = c.id WHERE c.name = :categoryName AND p.is_deleted = false", nativeQuery = true)
    List<Product> findAllByCategoryName(@Param("categoryName") String categoryName);

    @Query(value = "Select * from product where name =:name" ,nativeQuery = true)
    Product findByName(@Param("name") String name);

    @Query(value = "Select * from product where id =:id" ,nativeQuery = true)
    Product GetProductById(@Param("id") int id);



}
