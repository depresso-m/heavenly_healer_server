package com.pharmacy.heavenly_healer_server.service.impl;

import com.pharmacy.heavenly_healer_server.model.Medication;
import com.pharmacy.heavenly_healer_server.dto.MedicationDto;
import com.pharmacy.heavenly_healer_server.dto.MedicationLiteDto;
import com.pharmacy.heavenly_healer_server.repository.MedicationRepository;
import com.pharmacy.heavenly_healer_server.service.MedicationService;
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
import java.util.List;
import java.util.UUID;

// Сервис для работы с репозиторием. Прослойка между контроллером и репозиторием

@Service
@Primary
public class MedicationServiceImpl implements MedicationService {
    private final MedicationRepository repository;
    private final String uploadDir;
    private final String baseUrl;

    public MedicationServiceImpl(MedicationRepository medicationRepository,
                                 @Value("${file.upload-dir}") String uploadDir,
                                 @Value("${file.base-url}") String baseUrl) {
        this.repository = medicationRepository;
        this.uploadDir = uploadDir;
        this.baseUrl = baseUrl;
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
    public List<MedicationLiteDto> findByName(String name) {
        return repository.findAllByName(name);
    }

    public MedicationDto findById(Integer id) {
        Medication medication = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Medication not found with id: " + id));

        return MedicationDto.mapToDto(medication);
    }

    // Используется другими сервисами
    public Medication findMedicationEntityById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Medication not found with id: " + id));
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


    @Override
    public Resource getImage(String filename) throws Exception {
        Path filePath = Paths.get(uploadDir).resolve(filename).normalize();
        Resource resource = new UrlResource(filePath.toUri());
        if (!resource.exists() || !resource.isReadable()) {
            throw new Exception("Image not found: " + filename);
        }
        return resource;
    }

    @Override
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
            medication.setImagePath(baseUrl + fileName);
        }

        // Сохраняем медикамент в базе данных
        repository.save(medication);
    }

    @Override
    public List<MedicationLiteDto> findByCategory(Integer category_id) {
        return repository.findAllByCategory(category_id);
    }
}
