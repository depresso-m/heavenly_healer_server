package com.pharmacy.heavenly_healer_server.model;

import jakarta.persistence.*;
import lombok.*;

// Модель данных лекарства. Используется lombok и аннотация @Data для минимизации boilerplate кода

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "medications")
public class Medication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private float price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(name = "image_path")
    private String imagePath;

    private String brand;
    @Column(name = "expiry_date")
    private String expiryDate;
    @Column(name = "special_properties")
    private String specialProperties;
    private String country;
    @Column(name = "active_ingredient")
    private String activeIngredient;
    @Column(name = "release_form")
    private String releaseForm;
    private String dosage;
    private String manufacturer;
    @Column(name = "product_type")
    private String productType;
    @Column(name = "method_of_administration")
    private String methodOfAdministration;
    private String indications;
    private String composition;
    private String contraindications;
    @Column(name = "special_instructions")
    private String specialInstructions;
    private String packaging;
    @Column(name = "side_effects")
    private String sideEffects;
    @Column(name = "pharmacotherapeutic_group")
    private String pharmacotherapeuticGroup;
    @Column(name = "drug_interactions")
    private String drugInteractions;
    private String pharmacodynamics;
    @Column(name = "storage_temperature")
    private String storageTemperature;
    @Column(name = "dosage_form")
    private String dosageForm;
    private String pharmacokinetics;
    private String overdose;
    @Column(name = "special_storage_conditions")
    private String specialStorageConditions;
}