package com.pharmacy.heavenly_healer_server.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

// Модель данных заказа

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @Column(name = "total_price")
    private float totalPrice;

    private String status;

    @Column(name = "shipping_address")
    private String shippingAddress;


    // Не хранится в бд
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @JsonManagedReference // Чтобы избежать бесконечную рекурсию
    private List<OrderItem> orderItems;
}
