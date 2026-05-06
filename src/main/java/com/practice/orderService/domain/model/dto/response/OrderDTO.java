package com.practice.orderService.domain.model.dto.response;

import java.time.LocalDateTime;


public record OrderDTO(
    Long id,
    String userId,
    String status,
    Double totalPrice,
    LocalDateTime createdAt
) {}