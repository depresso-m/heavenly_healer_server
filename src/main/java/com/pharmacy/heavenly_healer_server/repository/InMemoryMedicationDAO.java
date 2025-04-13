package com.pharmacy.heavenly_healer_server.repository;

import com.pharmacy.heavenly_healer_server.model.Medication;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// Репозиторий для работы с данными в памяти, без БД. Используется для быстрого тестирования

@Repository
public class InMemoryMedicationDAO {
    private final List<Medication> MEDICATIONS = new ArrayList<>();

    public List<Medication> findAllMedication() {
        return MEDICATIONS;
    }

    public Medication saveMedication(Medication medication) {
        MEDICATIONS.add(medication);
        return medication;
    }

    public List<Medication> findByName(String name) {
        return MEDICATIONS.stream()
                .filter(element -> element.getName().equals(name))
                .collect(Collectors.toList());
    }

    public Medication updateMedication(Medication medication) {
        var medicationIndex = IntStream.range(0, MEDICATIONS.size())
                .filter(index -> MEDICATIONS.get(index).getName().equals(medication.getName()))
                .findFirst()
                .orElse(-1);
        if (medicationIndex > -1) {
            MEDICATIONS.set(medicationIndex, medication);
            return medication;
        }
        return null;
    }

    public void deleteMedication(String name) {
        var medication = findByName(name);
        if (medication != null)
        {
            MEDICATIONS.remove(medication);
        }
    }
}
