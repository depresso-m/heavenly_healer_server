package com.pharmacy.heavenly_healer_server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class MedicationLiteDto {
    private Integer id;
    private String name;
    private float price;
    private Integer categoryId;
    private String imagePath;
}
