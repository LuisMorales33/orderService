package com.practice.orderService.application.usecases;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.practice.orderService.domain.model.Order;
import com.practice.orderService.domain.model.OrderStatus;
import com.practice.orderService.domain.model.dto.request.CreateOrderRequest;
import com.practice.orderService.domain.ports.CachePort;
import com.practice.orderService.domain.ports.OrderRepositoryPort;

import io.micrometer.observation.annotation.Observed;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class CreateOrderUseCase {
    
    private final OrderRepositoryPort orderRepository;
    private final CachePort<Order> cache;

    public CreateOrderUseCase(OrderRepositoryPort orderRepository,
            CachePort<Order> cache
    ) {
        this.orderRepository = orderRepository;
        this.cache = cache;
    }

    // CREA UNA NUEVA ORDEN
    @Observed(name = "usecase.create-order")
    public Order execute(CreateOrderRequest request) {

        Order order = new Order();
        order.setUserId(request.userId());
        order.setStatus(OrderStatus.CREATED);
        order.setTotalPrice(request.totalPrice());
        order.setCreatedAt(LocalDateTime.now());

        log.info("Creating order for user: {}", request.userId(), " with total price: {}", request.totalPrice());

        // Save to DB
        Order createdOrder = orderRepository.save(order);

        // Save to cache
        cache.save(createdOrder.getId().toString(), createdOrder);

        log.info("Order created with id: {}", createdOrder.getId());

        return createdOrder;
    }

}
