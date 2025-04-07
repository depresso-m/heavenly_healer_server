package com.pharmacy.heavenly_healer_server.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    @GeneratedValue
    private Integer id;
    private String name;
    private String description;
    private float price;
}
