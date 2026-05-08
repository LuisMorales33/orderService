package com.practice.orderService.domain.model.dto.request;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record CreateOrderRequest(

    @NotNull(message = "userId is required")
    String userId,

    @NotNull(message = "totalPrice is required")
    @Min(value = 1, message = "totalPrice must be greater than 0")
    Double totalPrice

) {}