package com.pharmacy.heavenly_healer_server.controller;

import com.pharmacy.heavenly_healer_server.model.Medication;
import com.pharmacy.heavenly_healer_server.service.MedicationService;
import org.springframework.web.bind.annotation.*;

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
        //todo
        return medicationService.findAllMedication();
    }

    @PostMapping("saveMedication")
    public String saveMedication(@RequestBody Medication medication) {
        medicationService.saveMedication(medication);
        return "Medication been successfully saved";
    }

    @GetMapping("/{name}")
    public Medication findByName(@PathVariable String name) {
        return medicationService.findByName(name);
    }

    @PutMapping("update_medication")
    public Medication updateMedication(@RequestBody Medication medication) {
        return medicationService.updateMedication(medication);
    }

    @DeleteMapping("delete_medication/{name}")
    public void deleteMedication(@PathVariable String name)
    {
        medicationService.deleteMedication(name);
    }
}
