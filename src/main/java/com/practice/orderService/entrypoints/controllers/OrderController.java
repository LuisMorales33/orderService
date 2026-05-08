package com.practice.orderService.entrypoints.controllers;

import com.practice.orderService.application.usecases.CreateOrderUseCase;
import com.practice.orderService.application.usecases.GetOrderUseCase;
import com.practice.orderService.application.usecases.UpdateOrderStatusUseCase;
import com.practice.orderService.domain.model.Order;
import com.practice.orderService.domain.model.dto.request.CreateOrderRequest;
import com.practice.orderService.domain.model.dto.request.ReadOrderRequest;
import com.practice.orderService.domain.model.dto.request.UpdateOrderStatusRequest;
import com.practice.orderService.domain.model.dto.response.OrderDTO;
import com.practice.orderService.domain.model.dto.response.Response;
import com.practice.orderService.domain.model.mapper.OrderMapper;
import com.practice.orderService.domain.model.mapper.ResponseMapper;
import com.practice.orderService.utils.Constants;

import io.micrometer.observation.annotation.Observed;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/orders")
public class OrderController {

    private final CreateOrderUseCase createOrderUseCase;
    private final GetOrderUseCase getOrderUseCase;
    private final UpdateOrderStatusUseCase updateOrderStatusUseCase;
    private final ResponseMapper responseMapper;

    public OrderController(
        CreateOrderUseCase createOrderUseCase,
        GetOrderUseCase getOrderUseCase,
        UpdateOrderStatusUseCase updateOrderStatusUseCase,
        ResponseMapper responseMapper
    ) {
        this.createOrderUseCase = createOrderUseCase;
        this.getOrderUseCase = getOrderUseCase;
        this.updateOrderStatusUseCase = updateOrderStatusUseCase;
        this.responseMapper = responseMapper;
    }

    @Observed(name = "controller.create-order")
    @PostMapping
    public ResponseEntity<Response<OrderDTO>> createOrder(
        @Valid @RequestBody CreateOrderRequest request
    ) {

        Order order = createOrderUseCase.execute(request);

        return responseMapper.created(
            OrderMapper.toDTO(order),
            Constants.ORDER_ADD_SUCCESS
        );
    }

    @Observed(name = "controller.get-all-orders")
    @GetMapping
    public ResponseEntity<Response<List<OrderDTO>>> getAllOrders() {
        return responseMapper.success(
            getOrderUseCase.findAll().stream()
                .map(OrderMapper::toDTO)
                .toList(),
            Constants.ORDER_FOUND_SUCCESS
        );
    }
    
    @Observed(name = "controller.get-order-by-id")
    @GetMapping("/{id}")
    public ResponseEntity<Response<OrderDTO>> getOrderById(@Valid ReadOrderRequest request) throws Throwable 
        {
        Order order = (Order) getOrderUseCase.findById(request.id())
            .orElseThrow(() -> new RuntimeException(Constants.ORDER_NOT_FOUND_MESSAGE));

        return responseMapper.success(
            OrderMapper.toDTO(order),
            Constants.ORDER_FOUND_SUCCESS
        );
    }    

    @PutMapping("/{id}/status")
    @Observed(name = "controller.update-order-status")
    public ResponseEntity<Response<OrderDTO>> updateStatus(
        @Valid @RequestBody UpdateOrderStatusRequest request
    ) {

        Order order = updateOrderStatusUseCase.updateStatus(request.id(), request.status());

        return responseMapper.success(
            OrderMapper.toDTO(order),
            Constants.ORDER_UPDATED_SUCCESS
        );
    }
}