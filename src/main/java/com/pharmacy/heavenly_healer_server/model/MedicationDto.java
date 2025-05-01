package com.pharmacy.heavenly_healer_server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicationDto {
    private Integer id;
    private String name;
    private float price;
    private Integer category; // только ID категории
    private String imagePath;
    private String brand;
    private String expiryDate;
    private String specialProperties;
    private String country;
    private String activeIngredient;
    private String releaseForm;
    private String dosage;
    private String manufacturer;
    private String productType;
    private String methodOfAdministration;
    private String indications;
    private String composition;
    private String contraindications;
    private String specialInstructions;
    private String packaging;
    private String sideEffects;
    private String pharmacotherapeuticGroup;
    private String drugInteractions;
    private String pharmacodynamics;
    private String storageTemperature;
    private String dosageForm;
    private String pharmacokinetics;
    private String overdose;
    private String specialStorageConditions;

    public static MedicationDto mapToDto(Medication m) {
        return new MedicationDto(
                m.getId(),
                m.getName(),
                m.getPrice(),
                m.getCategory().getId(), // <-- только ID категории
                m.getImagePath(),
                m.getBrand(),
                m.getExpiryDate(),
                m.getSpecialProperties(),
                m.getCountry(),
                m.getActiveIngredient(),
                m.getReleaseForm(),
                m.getDosage(),
                m.getManufacturer(),
                m.getProductType(),
                m.getMethodOfAdministration(),
                m.getIndications(),
                m.getComposition(),
                m.getContraindications(),
                m.getSpecialInstructions(),
                m.getPackaging(),
                m.getSideEffects(),
                m.getPharmacotherapeuticGroup(),
                m.getDrugInteractions(),
                m.getPharmacodynamics(),
                m.getStorageTemperature(),
                m.getDosageForm(),
                m.getPharmacokinetics(),
                m.getOverdose(),
                m.getSpecialStorageConditions()
        );
    }

}
