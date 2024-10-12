package com.ecommerce.ecommerse.Repo;

import com.ecommerce.ecommerse.Models.FAQs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FAQsRepo extends JpaRepository<FAQs, Integer> {

}
