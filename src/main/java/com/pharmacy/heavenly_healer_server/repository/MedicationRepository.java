package com.pharmacy.heavenly_healer_server.repository;

import com.pharmacy.heavenly_healer_server.model.Medication;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MedicationRepository extends JpaRepository<Medication, Integer> {
    void deleteByName(String name);
    Medication findByName(String name);

}
