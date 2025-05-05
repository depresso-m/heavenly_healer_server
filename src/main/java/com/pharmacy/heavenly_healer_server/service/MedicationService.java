package com.pharmacy.heavenly_healer_server.service;

import com.pharmacy.heavenly_healer_server.model.Medication;
import com.pharmacy.heavenly_healer_server.dto.MedicationDto;
import com.pharmacy.heavenly_healer_server.dto.MedicationLiteDto;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

// Общий интерфейс для сервиса

public interface MedicationService {
    List<Medication> findAllMedication();

    Medication saveMedication(Medication medication);
    List<MedicationLiteDto> findByName(String name);
    Medication updateMedication(Medication medication);
    void deleteMedication(String name);
    Resource getImage(String filename) throws Exception;
    void saveMedicationWithImage(Medication medication, MultipartFile image) throws Exception; // Сохранение с картинкой
    MedicationDto findById(Integer id);
    List<MedicationLiteDto> findByCategory(Integer category_id);
    Medication findMedicationEntityById(Integer id);
}
