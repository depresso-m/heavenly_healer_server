package com.pharmacy.heavenly_healer_server.service.impl;

import com.pharmacy.heavenly_healer_server.model.Category;
import com.pharmacy.heavenly_healer_server.repository.CategoryRepository;
import com.pharmacy.heavenly_healer_server.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}
