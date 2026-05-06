package com.practice.orderService.domain.ports;

import java.util.List;
import java.util.Optional;

import com.practice.orderService.domain.model.Order;

public interface OrderRepositoryPort {
    Order save(Order order);

    Optional<Order> findById(Long id);

    List<Order> findAll();

}
