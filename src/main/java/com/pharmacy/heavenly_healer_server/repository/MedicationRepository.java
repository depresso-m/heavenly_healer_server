package com.pharmacy.heavenly_healer_server.repository;

import com.pharmacy.heavenly_healer_server.model.Medication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface MedicationRepository extends JpaRepository<Medication, Integer> {
    void deleteByName(String name);
    List<Medication> findByName(String name);
    List<Medication> findByNameContainingIgnoreCase(String name);
}
