package com.pharmacy.heavenly_healer_server.controller;

import com.pharmacy.heavenly_healer_server.model.Medication;
import com.pharmacy.heavenly_healer_server.dto.MedicationDto;
import com.pharmacy.heavenly_healer_server.dto.MedicationLiteDto;
import com.pharmacy.heavenly_healer_server.service.MedicationService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

// Контроллер приложения нужно ТОЛЬКО для получения запросов из вне и вызова соответственных методов у сервиса

@RestController
@RequestMapping("/api/v1/medications")
public class MedicationController {

    private final MedicationService medicationService;

    public MedicationController(MedicationService medicationService) {
        this.medicationService = medicationService;
    }

    @GetMapping
    public List<Medication> findAllMedication() {
        return medicationService.findAllMedication();
    }

    @PostMapping(value = "/saveMedication", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> saveMedication(
            @RequestPart("medication") Medication medication,
            @RequestPart(value = "image", required = false) MultipartFile image) {
        try {
            medicationService.saveMedicationWithImage(medication, image);
            return ResponseEntity.ok("Medication successfully saved");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to save medication: " + e.getMessage());
        }
    }

    @GetMapping("/images/{filename}")
    public ResponseEntity<Resource> getImage(@PathVariable String filename) {
        try {
            Resource imageResource = medicationService.getImage(filename);
            if (imageResource.exists() && imageResource.isReadable()) {
                return ResponseEntity.ok()
                        .contentType(MediaType.IMAGE_JPEG) // Можно динамически определять тип
                        .body(imageResource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @GetMapping("/by-name/{name}")
    public List<MedicationLiteDto> findByName(@PathVariable String name) {
        return medicationService.findByName(name);
    }

    @GetMapping("/by-category/{category_id}")
    public List<MedicationLiteDto> findByCategory(@PathVariable Integer category_id) {
        return medicationService.findByCategory(category_id);
    }

    @GetMapping("/{id}")
    public MedicationDto findById(@PathVariable Integer id) {
        return medicationService.findById(id);
    }


    @PutMapping("update_medication")
    public Medication updateMedication(@RequestBody Medication medication) {
        return medicationService.updateMedication(medication);
    }

    @DeleteMapping("delete_medication/{name}")
    public void deleteMedication(@PathVariable String name) {
        medicationService.deleteMedication(name);
    }
}
