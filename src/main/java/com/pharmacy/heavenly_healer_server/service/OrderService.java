package com.pharmacy.heavenly_healer_server.service;

import com.pharmacy.heavenly_healer_server.dto.OrderDto;
import com.pharmacy.heavenly_healer_server.dto.OrderItemDto;
import com.pharmacy.heavenly_healer_server.model.Order;
import com.pharmacy.heavenly_healer_server.model.OrderItem;
import com.pharmacy.heavenly_healer_server.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<OrderDto> getOrdersByUserId(Integer userId) {
        List<Order> orders = orderRepository.findByUserId(userId);
        List<OrderDto> result = new ArrayList<>();
        for (Order order : orders) {
            result.add(mapToDto(order));
        }
        return result;
    }

    public OrderDto saveOrder(Order order) {
        for (OrderItem orderItem : order.getOrderItems()) {
            orderItem.setOrder(order);
        }
        Order savedOrder = orderRepository.save(order);
        return mapToDto(savedOrder);
    }

    public OrderDto mapToDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setOrderDate(order.getOrderDate().toString());
        orderDto.setTotalPrice(order.getTotalPrice());
        orderDto.setStatus(order.getStatus());
        orderDto.setShippingAddress(order.getShippingAddress());

        List<OrderItemDto> itemDtos = order.getOrderItems().stream().map(item ->
                {
                    OrderItemDto orderItemDto = new OrderItemDto();
                    orderItemDto.setId(item.getId());
                    orderItemDto.setMedicationId(item.getMedication().getId());
                    orderItemDto.setMedicationName(item.getMedication().getName());
                    orderItemDto.setQuantity(item.getQuantity());
                    orderItemDto.setUnitPrice(item.getUnitPrice());
                    return orderItemDto;
                })
                .collect(Collectors.toList());

        orderDto.setOrderItems(itemDtos);
        return orderDto;
    }

    public OrderDto getOrderById(Integer id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
        return mapToDto(order);
    }
}
