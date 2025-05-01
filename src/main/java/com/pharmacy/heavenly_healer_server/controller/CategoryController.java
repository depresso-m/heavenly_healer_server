package com.pharmacy.heavenly_healer_server.controller;

import com.pharmacy.heavenly_healer_server.model.Category;
import com.pharmacy.heavenly_healer_server.service.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<Category> getAllCategories()
    {
        return categoryService.getAllCategories();
    }
}
