package com.practice.orderService.application.usecases;

import java.util.List;
import java.util.Optional;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.practice.orderService.domain.model.Order;
import com.practice.orderService.domain.ports.CachePort;
import com.practice.orderService.domain.ports.OrderRepositoryPort;

import io.micrometer.observation.annotation.Observed;

public class GetOrderUseCase  {

    private static final Logger log =
            LoggerFactory.getLogger(GetOrderUseCase.class);

        private final OrderRepositoryPort orderRepository;
        private final CachePort<Order> cache;


    public GetOrderUseCase(
            OrderRepositoryPort orderRepository,
            CachePort<Order> cache
    ) {
        this.orderRepository = orderRepository;
        this.cache = cache;
    }

    
    @Observed(name = "get-order-by-id")
    public Optional<Order> findById(Long id) {

        log.info("Searching order with id {}", id);

        String cacheKey = "order:" + id;


        Optional<Order> cachedOrder = cache.get(cacheKey);

        if (cachedOrder.isPresent()) {
            log.info("Order found in Redis cache");
            return cachedOrder;
        }

        log.info("Order not found in cache. Querying database");

        // DB
        Optional<Order> order = orderRepository.findById(id);

        order.ifPresent(o -> {
            log.info("Saving order into Redis cache");
            cache.save(cacheKey, o);
        });

        return order;
    }


    @Observed(name = "get-all-orders")
    public List<Order> findAll() {
        log.info("Searching all orders");
        
        return orderRepository.findAll();
    }

}
