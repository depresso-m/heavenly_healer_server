package com.pharmacy.heavenly_healer_server.service;

import com.pharmacy.heavenly_healer_server.model.Medication;
import com.pharmacy.heavenly_healer_server.dto.MedicationDto;
import com.pharmacy.heavenly_healer_server.dto.MedicationLiteDto;
import com.pharmacy.heavenly_healer_server.repository.MedicationRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

// Сервис для работы с репозиторием. Прослойка между контроллером и репозиторием

@Service
@Primary
public class MedicationService {
    private final MedicationRepository repository;
    private final String uploadDir;
    private final String baseUrl;

    public MedicationService(MedicationRepository medicationRepository,
                             @Value("${file.upload-dir}") String uploadDir,
                             @Value("${file.base-url}") String baseUrl) {
        this.repository = medicationRepository;
        this.uploadDir = uploadDir;
        this.baseUrl = baseUrl;
    }

    public void saveMedicationWithImage(Medication medication, MultipartFile image) throws Exception {
        if (image != null && !image.isEmpty()) {
            // Валидация типа файла
            if (!image.getContentType().startsWith("image/")) {
                throw new IllegalArgumentException("Only image files are allowed");
            }
            // Валидация размера файла (до 5MB)
            if (image.getSize() > 5 * 1024 * 1024) {
                throw new IllegalArgumentException("File size exceeds 5MB limit");
            }

            // Генерируем уникальное имя файла
            String fileName = UUID.randomUUID() + "_" + image.getOriginalFilename();
            File destinationFile = new File(uploadDir + File.separator + fileName);

            // Сохраняем файл на диск
            image.transferTo(destinationFile);

            // Устанавливаем путь к файлу в сущности
            medication.setImagePath(fileName);
        }

        // Сохраняем медикамент в базе данных
        repository.save(medication);
    }

    public List<MedicationDto> findAllMedication() {
        List<Medication> medications = repository.findAll();
        List<MedicationDto> result = new ArrayList<>();
        for (Medication medication : medications) {
            MedicationDto dto= MedicationDto.mapToDto(medication);
            formatImagePath(dto);
            result.add(dto);
        }
        return result;
    }

    public List<MedicationLiteDto> findByName(String name) {
        List<MedicationLiteDto> medications = repository.findAllByName(name);
        for (MedicationLiteDto medication : medications) {
            formatImagePath(medication);
        }
        return medications;
    }

    public MedicationDto findById(Integer id) {
        Medication medication = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Medication not found with id: " + id));
        MedicationDto dto = MedicationDto.mapToDto(medication);
        formatImagePath(dto);
        return dto;
    }

    // Используется другими сервисами
    public Medication findMedicationEntityById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Medication not found with id: " + id));
    }

    public Medication updateMedication(Medication medication) {
        return repository.save(medication);
    }

    @Transactional //Обязательная аннотация для метода удаления
    public void deleteMedication(String name) {
        repository.deleteByName(name);
    }


    public Resource getImage(String filename) throws Exception {
        Path filePath = Paths.get(uploadDir).resolve(filename).normalize();
        Resource resource = new UrlResource(filePath.toUri());
        if (!resource.exists() || !resource.isReadable()) {
            throw new Exception("Image not found: " + filename);
        }
        return resource;
    }

    public List<MedicationLiteDto> findByCategory(Integer category_id) {
        List<MedicationLiteDto> medications = repository.findAllByCategory(category_id);
        for (MedicationLiteDto medication : medications) {
            formatImagePath(medication);
        }
        return medications;
    }
    public void formatImagePath (MedicationDto medication)
    {
        String imagePath = medication.getImagePath();
        medication.setImagePath(baseUrl+imagePath);
    }

    public void formatImagePath (MedicationLiteDto medication)
    {
        String imagePath = medication.getImagePath();
        medication.setImagePath(baseUrl+imagePath);
    }
}
