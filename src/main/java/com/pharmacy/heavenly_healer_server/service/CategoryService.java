package com.pharmacy.heavenly_healer_server.service;

import com.pharmacy.heavenly_healer_server.model.Category;
import com.pharmacy.heavenly_healer_server.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}
