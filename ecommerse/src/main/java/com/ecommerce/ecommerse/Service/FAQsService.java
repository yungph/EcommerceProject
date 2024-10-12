package com.ecommerce.ecommerse.Service;

import com.ecommerce.ecommerse.Models.FAQs;
import com.ecommerce.ecommerse.Repo.FAQsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FAQsService {
    @Autowired
    FAQsRepo faqRepository;

    public List<FAQs> getAllFAQs() {
        return faqRepository.findAll();
    }

    public Optional<FAQs> getFAQById(int id) {
        return faqRepository.findById(id);
    }

    public void createFAQ(FAQs faq) {
        faqRepository.save(faq);
    }

    public void updateFAQ( FAQs faq) {
        faqRepository.save(faq);
    }

    public void deleteFAQ(int id) {
        faqRepository.deleteById(id);
    }
}
