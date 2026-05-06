package com.practice.orderService.application.usecases;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.practice.orderService.domain.model.Order;
import com.practice.orderService.domain.model.OrderStatus;
import com.practice.orderService.domain.ports.OrderRepositoryPort;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class CreateOrderUseCase {
    
    private final OrderRepositoryPort orderRepository;

    public CreateOrderUseCase(OrderRepositoryPort orderRepository) {
        this.orderRepository = orderRepository;
    }

    // CREA UNA NUEVA ORDEN
    public Order execute(String userId, Double totalPrice) {
        Order order = new Order(null, userId, OrderStatus.CREATED, totalPrice, LocalDateTime.now());
        log.info("Creating order for user: {}", userId, " with total price: {}", totalPrice);
        return orderRepository.save(order);
    }

}
