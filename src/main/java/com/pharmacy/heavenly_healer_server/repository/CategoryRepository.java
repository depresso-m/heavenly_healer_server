package com.pharmacy.heavenly_healer_server.repository;

import com.pharmacy.heavenly_healer_server.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
