package com.practice.orderService.config;

import com.practice.orderService.application.usecases.CreateOrderUseCase;
import com.practice.orderService.application.usecases.GetOrderUseCase;
import com.practice.orderService.application.usecases.UpdateOrderStatusUseCase;
import com.practice.orderService.domain.model.Order;
import com.practice.orderService.domain.ports.CachePort;
import com.practice.orderService.domain.ports.OrderRepositoryPort;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    CreateOrderUseCase createOrderUseCase(OrderRepositoryPort port, CachePort<Order> cache) {
        return new CreateOrderUseCase(port, cache);
    }
    @Bean
    GetOrderUseCase getOrderUseCase(OrderRepositoryPort repositoryPort, CachePort<Order> cachePort) {
        return new GetOrderUseCase(repositoryPort, cachePort);
    }

    @Bean
    UpdateOrderStatusUseCase updateOrderStatusUseCase(OrderRepositoryPort port, CachePort<Order> cache) {
        return new UpdateOrderStatusUseCase(port, cache);
    }

}