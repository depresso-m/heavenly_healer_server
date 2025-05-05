package com.pharmacy.heavenly_healer_server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDto {
    private Integer id;
    private Integer userId;
    private Integer medicationId;
    private Integer quantity;
}
