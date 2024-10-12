package com.ecommerce.ecommerse.Service;

import com.ecommerce.ecommerse.Models.CoverImage;
import com.ecommerce.ecommerse.Repo.CoverImageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoverImageService {
    @Autowired
    CoverImageRepo coverImageRepo;

    public void setCoverImage(CoverImage coverImage) {
        coverImageRepo.save(coverImage);
    }

    public void UpdateCoverImage(CoverImage coverImage) {
        coverImageRepo.save(coverImage);
    }

    public List<CoverImage> getCoverImages() {
        return coverImageRepo.findAll();
    }

    public CoverImage getCoverImage(long id) {
        return coverImageRepo.findById(id).get();
    }

    public void deleteCoverImage(long id) {
        coverImageRepo.deleteById(id);
    }
}
