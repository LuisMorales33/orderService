package com.practice.orderService.domain.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private Long id;
    private String userId;
    private OrderStatus status;
    private Double totalPrice;
    private LocalDateTime createdAt;

}
