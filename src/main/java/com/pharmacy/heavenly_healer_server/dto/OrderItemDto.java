package com.pharmacy.heavenly_healer_server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDto {
    private Integer id;
    private Integer medicationId;
    private String medicationName;
    private Integer quantity;
    private float unitPrice;
}
