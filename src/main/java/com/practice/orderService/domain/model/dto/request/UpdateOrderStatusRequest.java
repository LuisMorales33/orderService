package com.practice.orderService.domain.model.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record UpdateOrderStatusRequest(

    @NotNull(message = "status is required")
    String status,

    @Min(value = 1, message = "id must be greater than 0")
    @NotNull(message = "id is required")
    Long id

) {}