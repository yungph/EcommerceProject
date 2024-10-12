package com.ecommerce.ecommerse.Repo;

import com.ecommerce.ecommerse.Models.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;




@Repository
public interface UserRepo extends JpaRepository<User, String> {

//    Optional<User> findByUsername(String username);
    User findByEmail(String email);
    User findByUsername(String username);

    @Query(value = "select * from user_details where id = :id",nativeQuery = true)
    User GetUserById(@Param("id") String id);

    @Query(value = "select * from user_details  WHERE email = :email", nativeQuery = true)
    User GetUserByEmail(@Param("email") String email);

    @Modifying
    @Query(value = "UPDATE user_details SET address = :address WHERE id = :id", nativeQuery = true)
    void updateAddress(@Param("id") String id, @Param("address") String address);

//    @Query(value = "SELECT * FROM product WHERE id IN (SELECT product_id FROM user_favorites WHERE user_id = :id)", nativeQuery = true)
//    Set<Product> GetFavProducts(@Param("id") String id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO user_roles (user_id, role_name) VALUES ( :id , 'ROLE_USER') ",nativeQuery = true)
    void makeUserRole(@Param("id") String id);

    @Modifying
    @Transactional
    @Query(value = "update user_roles  set role_name ='ROLE_ADMIN' where user_id=:id ",nativeQuery = true)
    void makeUserRoleAdmin(@Param("id") String id);




//    @Query(value = "SELECT p.id, p.name, p.price FROM product p WHERE p.id IN (SELECT uf.product_id FROM user_favorites uf WHERE uf.user_id = :id)", nativeQuery = true)
//    List<Product> GetFavProducts(@Param("id") String id);


}
