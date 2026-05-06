package com.practice.orderService.config;

import com.practice.orderService.application.usecases.CreateOrderUseCase;
import com.practice.orderService.application.usecases.GetOrderUseCase;
import com.practice.orderService.application.usecases.UpdateOrderStatusUseCase;
import com.practice.orderService.domain.ports.OrderRepositoryPort;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    CreateOrderUseCase createOrderUseCase(OrderRepositoryPort port) {
        return new CreateOrderUseCase(port);
    }
    @Bean
    GetOrderUseCase getOrderUseCase(OrderRepositoryPort port) {
        return new GetOrderUseCase(port);
    }

    @Bean
    UpdateOrderStatusUseCase updateOrderStatusUseCase(OrderRepositoryPort port) {
        return new UpdateOrderStatusUseCase(port);
    }

}