package com.pharmacy.heavenly_healer_server.service;

import com.pharmacy.heavenly_healer_server.model.Medication;

import java.util.List;

// Общий интерфейс для сервиса

public interface MedicationService {
    List<Medication> findAllMedication();

    Medication saveMedication(Medication medication);
    Medication findByName(String name);
    Medication updateMedication(Medication medication);
    void deleteMedication(String name);
}
