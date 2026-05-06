package com.practice.orderService.domain.model.mapper;

import java.time.LocalDateTime;

import com.practice.orderService.domain.model.dto.response.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ResponseMapper {

    public <T> ResponseEntity<Response<T>> success(T data, String message) {
        Response<T> response = new Response<>();
        response.setStatusCode(200);
        response.setMessage(message);
        response.setData(data);
        response.setTimestamp(LocalDateTime.now());

        return ResponseEntity.ok(response);
    }

    public <T> ResponseEntity<Response<T>> created(T data, String message) {
        Response<T> response = new Response<>();
        response.setStatusCode(201);
        response.setMessage(message);
        response.setData(data);
        response.setTimestamp(LocalDateTime.now());

        return ResponseEntity.status(201).body(response);
    }

    public ResponseEntity<Response<Object>> error(int status, String message, String error) {
        Response<Object> response = new Response<>();
        response.setStatusCode(status);
        response.setMessage(message);
        response.setError(error);
        response.setTimestamp(LocalDateTime.now());

        return ResponseEntity.status(status).body(response);
    }
}