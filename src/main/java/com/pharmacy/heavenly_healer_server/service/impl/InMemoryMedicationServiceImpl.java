package com.pharmacy.heavenly_healer_server.service.impl;

import com.pharmacy.heavenly_healer_server.model.Medication;
import com.pharmacy.heavenly_healer_server.repository.InMemoryMedicationDAO;
import com.pharmacy.heavenly_healer_server.service.MedicationService;
import org.springframework.stereotype.Service;

import java.util.List;

// Реализация интерфейса сервиса сохранения данных в памяти. Используется как прослойка между репозиторием и контроллером

@Service
public class InMemoryMedicationServiceImpl implements MedicationService {
    private final InMemoryMedicationDAO repository;

    public InMemoryMedicationServiceImpl(InMemoryMedicationDAO repository) {
        this.repository = repository;
    }

    @Override
    public List<Medication> findAllMedication() {
        return repository.findAllMedication();
    }

    @Override
    public Medication saveMedication(Medication medication) {
        return repository.saveMedication(medication);
    }

    @Override
    public Medication findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public Medication updateMedication(Medication medication) {
        return repository.updateMedication(medication);
    }

    @Override
    public void deleteMedication(String name) {
        repository.deleteMedication(name);
    }
}
