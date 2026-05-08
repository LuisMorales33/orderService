package com.practice.orderService.config;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import com.practice.orderService.domain.exceptions.BusinessException;
import com.practice.orderService.domain.model.dto.response.Response;
import com.practice.orderService.domain.model.mapper.ResponseMapper;
import com.practice.orderService.utils.Constants;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final ResponseMapper responseMapper;

    public GlobalExceptionHandler(ResponseMapper responseMapper) {
        this.responseMapper = responseMapper;
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Response<Object>> handleRuntime(RuntimeException ex) {
        return responseMapper.error(404, Constants.ORDER_NOT_FOUND_MESSAGE, ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response<Object>> handleValidation(MethodArgumentNotValidException ex) {
        String error = ex.getBindingResult().getFieldError().getDefaultMessage();
        return responseMapper.error(400, Constants.INVALID_CONTENT_MESSAGE, error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response<Object>> handleGeneral(Exception ex) {
        return responseMapper.error(500, Constants.INTERNAL_SERVER_ERROR, ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Response<Object>> handleIllegalArgument(IllegalArgumentException ex) {
        return responseMapper.error(400, Constants.INVALID_CONTENT_MESSAGE, ex.getMessage());
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Response<Object>> handleBusiness(BusinessException ex) {
        return responseMapper.error(422, Constants.NOT_PROCESSED_MESSAGE, ex.getMessage());
    }


}