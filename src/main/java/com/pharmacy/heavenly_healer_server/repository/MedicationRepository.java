package com.pharmacy.heavenly_healer_server.repository;

import com.pharmacy.heavenly_healer_server.model.Medication;
import com.pharmacy.heavenly_healer_server.dto.MedicationLiteDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface MedicationRepository extends JpaRepository<Medication, Integer> {
    void deleteByName(String name);

    List<Medication> findByName(String name);

    @Query("SELECT new com.pharmacy.heavenly_healer_server.dto.MedicationLiteDto(m.id, m.name, m.price, m.category.id, m.imagePath) " +
            "FROM Medication m " +
            "WHERE LOWER(m.name) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<MedicationLiteDto> findAllByName(@Param("query") String query);

    @Query("SELECT new com.pharmacy.heavenly_healer_server.dto.MedicationLiteDto(m.id, m.name, m.price, m.category.id, m.imagePath) " +
            "FROM Medication m " +
            "WHERE m.category.id = :categoryId")
    List<MedicationLiteDto> findAllByCategory(@Param("categoryId") Integer categoryId);
}