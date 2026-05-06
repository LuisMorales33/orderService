package com.practice.orderService.infrastructure.adapters.persistence;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.practice.orderService.domain.model.Order;
import com.practice.orderService.domain.ports.OrderRepositoryPort;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class OrderRepositoryAdapter implements OrderRepositoryPort {

    private final JpaOrderRepository jpaRepository;

    public OrderRepositoryAdapter(JpaOrderRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Order save(Order order) {
        log.info("Saving order for user: {}", order.getUserId(), " with total price: {}", order.getTotalPrice());
        OrderEntity entity = new OrderEntity();
        entity.setId(order.getId() != null ? order.getId() : null);
        entity.setUserId(order.getUserId());
        entity.setStatus(order.getStatus());
        entity.setTotalPrice(order.getTotalPrice());
        entity.setCreatedAt(order.getCreatedAt());

        OrderEntity saved = jpaRepository.save(entity);

        return new Order(saved.getId(), saved.getUserId(), saved.getStatus(), saved.getTotalPrice(), saved.getCreatedAt());
    }

    @Override
    public Optional<Order> findById(Long id) {
        return jpaRepository.findById(id)
                .map(entity -> new Order(entity.getId(), entity.getUserId(), entity.getStatus(), entity.getTotalPrice(), entity.getCreatedAt()));
    }

    @Override
    public List<Order> findAll() {
        return jpaRepository.findAll().stream()
                .map(entity -> new Order(entity.getId(), entity.getUserId(), entity.getStatus(), entity.getTotalPrice(), entity.getCreatedAt()))
                .collect(Collectors.toList());
    }

}