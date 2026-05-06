package com.practice.orderService.domain.model.mapper;

import com.practice.orderService.domain.model.Order;
import com.practice.orderService.domain.model.dto.response.OrderDTO;

public class OrderMapper {

    public static OrderDTO toDTO(Order order) {
        return new OrderDTO(
            order.getId(),
            order.getUserId(),
            order.getStatus().name(),
            order.getTotalPrice(),
            order.getCreatedAt()
        );
    }
}