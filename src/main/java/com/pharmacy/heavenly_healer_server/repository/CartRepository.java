package com.pharmacy.heavenly_healer_server.repository;

import com.pharmacy.heavenly_healer_server.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    List<Cart> findByUserId(Integer userId);
    void deleteByUserId(Integer userId);
    Optional<Cart> findByUserIdAndMedicationId(Integer userId, Integer medicationId);
}
