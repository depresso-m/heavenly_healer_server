package com.pharmacy.heavenly_healer_server.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class MedicationLiteDto {
    private Integer id;
    private String name;
    private float price;
    private String imagePath;
}
