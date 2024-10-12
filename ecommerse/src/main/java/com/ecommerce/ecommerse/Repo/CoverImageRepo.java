package com.ecommerce.ecommerse.Repo;

import com.ecommerce.ecommerse.Models.CoverImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
public interface CoverImageRepo extends JpaRepository<CoverImage, Long> {
}
