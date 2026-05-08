package com.practice.orderService.application.usecases;

import com.practice.orderService.domain.exceptions.BusinessException;
import com.practice.orderService.domain.model.Order;
import com.practice.orderService.domain.model.OrderStatus;
import com.practice.orderService.domain.ports.CachePort;
import com.practice.orderService.domain.ports.OrderRepositoryPort;
import com.practice.orderService.utils.Constants;

import io.micrometer.observation.annotation.Observed;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class UpdateOrderStatusUseCase {

    private final OrderRepositoryPort orderRepository;
    private final CachePort<Order> cache;

    public UpdateOrderStatusUseCase(OrderRepositoryPort orderRepository, CachePort<Order> cache) {
        this.orderRepository = orderRepository;
        this.cache = cache;
    }

    @Observed(name = "usecase.update-order-status")
    public Order updateStatus(Long id, String status) {

        OrderStatus newStatus = OrderStatus.valueOf(status.toUpperCase());
        
        if(newStatus != null) {
            return orderRepository.findById(id)
                .map(order -> {
                    if (order.getStatus().equals(newStatus)) {
                        throw new BusinessException("Order is already in status: " + newStatus);
                    }
                    log.info("Updating order {} status from {} to {}", id, order.getStatus(), newStatus);
                    order.setStatus(newStatus);
                    Order updatedOrder = orderRepository.save(order);
                    cache.save(updatedOrder.getId().toString(), updatedOrder);

                    return updatedOrder;
                })
                .orElseThrow(() -> new RuntimeException(Constants.ORDER_NOT_FOUND_MESSAGE + id));
        } else {
            throw new IllegalArgumentException(Constants.INVALID_CONTENT_MESSAGE + status);
        }
    }

}
