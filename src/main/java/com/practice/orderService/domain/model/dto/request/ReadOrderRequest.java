package com.practice.orderService.domain.model.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ReadOrderRequest(

    @NotNull(message = "id is required")
    @Min(value = 1, message = "id must be greater than 0")
    Long id

) {}
