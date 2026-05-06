package com.practice.orderService.application.usecases;

import com.practice.orderService.domain.model.Order;
import com.practice.orderService.domain.model.OrderStatus;
import com.practice.orderService.domain.ports.OrderRepositoryPort;

public class UpdateOrderStatusUseCase {

    private final OrderRepositoryPort orderRepository;
    
    public UpdateOrderStatusUseCase(OrderRepositoryPort orderRepository) {
        this.orderRepository = orderRepository;    
    }

    public Order updateStatus(Long id, String status) {

        OrderStatus newStatus = OrderStatus.valueOf(status.toUpperCase());
        
        if(newStatus != null) {
            return orderRepository.findById(id)
                .map(order -> {
                    order.setStatus(newStatus);
                    return orderRepository.save(order);
                })
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
        
        } else {
            throw new IllegalArgumentException("Invalid order status: " + status);
        }
        
    }

}
