package com.practice.orderService.application.usecases;

import java.util.List;
import java.util.Optional;

import com.practice.orderService.domain.model.Order;
import com.practice.orderService.domain.ports.OrderRepositoryPort;

public class GetOrderUseCase  {

        private final OrderRepositoryPort orderRepository;

    public GetOrderUseCase(OrderRepositoryPort orderRepository) {
        this.orderRepository = orderRepository;
    }

    
    public Optional findById(Long id) {
        return orderRepository.findById(id);
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

}
