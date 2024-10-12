package com.ecommerce.ecommerse.Service;

import com.ecommerce.ecommerse.Models.SocialMediaLink;
import com.ecommerce.ecommerse.Repo.SocialMediaLinkRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SocialMediaLinkService {

    @Autowired
    SocialMediaLinkRepo repo;

    public void SetSocialMediaLink(SocialMediaLink link) {
        repo.save(link);
    }

    public void UpdateSocialMediaLink(SocialMediaLink link) {
        repo.save(link);
    }

    public SocialMediaLink GetSocialMediaLink(String platformname) {
        return repo.findByPlatform(platformname);
    }

    public List<SocialMediaLink> GetAllSocialMediaLink() {
        return repo.findAll();
    }

    public void DeleteSocialMediaLink(SocialMediaLink link) {
        repo.delete(link);
    }
    public void DeleteByPlatform(String platformname) {
        repo.DeleteByPlatform(platformname);
    }

}
