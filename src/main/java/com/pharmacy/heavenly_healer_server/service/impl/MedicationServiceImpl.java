package com.pharmacy.heavenly_healer_server.service.impl;

import com.pharmacy.heavenly_healer_server.model.Medication;
import com.pharmacy.heavenly_healer_server.repository.MedicationRepository;
import com.pharmacy.heavenly_healer_server.service.MedicationService;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

// Сервис для работы с репозиторием. Прослойка между контроллером и репозиторием

@Service
@Primary
public class MedicationServiceImpl implements MedicationService {
    private final MedicationRepository repository;

    public MedicationServiceImpl(MedicationRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Medication> findAllMedication() {
        return repository.findAll();
    }

    @Override
    public Medication saveMedication(Medication medication) {
        return repository.save(medication);
    }

    @Override
    public Medication findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public Medication updateMedication(Medication medication) {
        return repository.save(medication);
    }

    @Override
    @Transactional //Обязательная аннотация для метода удаления
    public void deleteMedication(String name) {
        repository.deleteByName(name);
    }
}
