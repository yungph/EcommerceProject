package com.ecommerce.ecommerse.Repo;

import com.ecommerce.ecommerse.Models.SocialMediaLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface SocialMediaLinkRepo extends JpaRepository<SocialMediaLink, Integer> {
    @Query(value = "select * from links where platform=:platform",nativeQuery = true)
    SocialMediaLink findByPlatform(@Param("platform") String platform);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM links WHERE platform=:platform" , nativeQuery = true)
    void DeleteByPlatform(@Param("platform") String platform);
}
