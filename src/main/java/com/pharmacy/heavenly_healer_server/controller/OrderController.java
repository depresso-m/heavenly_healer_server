package com.pharmacy.heavenly_healer_server.controller;

import com.pharmacy.heavenly_healer_server.dto.OrderDto;
import com.pharmacy.heavenly_healer_server.model.Order;
import com.pharmacy.heavenly_healer_server.service.OrderService;
import com.pharmacy.heavenly_healer_server.util.MyUserDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderDto> saveOrder(@RequestBody Order order) {
        OrderDto saveOrder = orderService.saveOrder(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveOrder);
    }

    @GetMapping("/by-id")
    public ResponseEntity<List<OrderDto>> getOrderById() {
        Integer userId = ((MyUserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal()).getId();
        List<OrderDto> list = orderService.getOrdersByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable Integer id) {
        OrderDto order = orderService.getOrderById(id);
        return ResponseEntity.status(HttpStatus.OK).body(order);
    }
}
