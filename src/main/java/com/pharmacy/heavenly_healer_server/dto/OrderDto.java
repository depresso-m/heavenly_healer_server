package com.pharmacy.heavenly_healer_server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Integer id;
    private String orderDate;
    private float totalPrice;
    private String status;
    private String shippingAddress;
    private List<OrderItemDto> orderItems;
}
