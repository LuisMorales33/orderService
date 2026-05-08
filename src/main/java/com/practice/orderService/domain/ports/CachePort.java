package com.practice.orderService.domain.ports;

import java.util.Optional;

public interface CachePort<T> {

    void save(String key, T value);

    Optional<T> get(String key);

    void delete(String key);
}