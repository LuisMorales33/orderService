package com.practice.orderService.infrastructure.adapters.cache;

import java.time.Duration;
import java.util.Optional;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.practice.orderService.domain.model.Order;
import com.practice.orderService.domain.ports.CachePort;

@Component
public class RedisAdapter implements CachePort<Order> {

    private final RedisTemplate<String, Order> redisTemplate;

    public RedisAdapter(RedisTemplate<String, Order> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void save(String key, Order value) {

        redisTemplate.opsForValue()
                .set(key, value, Duration.ofMinutes(10));
    }

    @Override
    public Optional<Order> get(String key) {

        return Optional.ofNullable(
                redisTemplate.opsForValue().get(key)
        );
    }

    @Override
    public void delete(String key) {

        redisTemplate.delete(key);
    }
}