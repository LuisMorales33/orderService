package com.practice.orderService.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import com.practice.orderService.domain.model.Order;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Bean
    RedisTemplate<String, Order> redisTemplate(
            RedisConnectionFactory connectionFactory
    ) {

        RedisTemplate<String, Order> template =
                new RedisTemplate<>();

        template.setConnectionFactory(connectionFactory);

        // ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.registerModule(new JavaTimeModule());

        objectMapper.disable(
                SerializationFeature.WRITE_DATES_AS_TIMESTAMPS
        );

        // Serializer tipado
        Jackson2JsonRedisSerializer<Order> serializer =
        new Jackson2JsonRedisSerializer<>(objectMapper, Order.class);

        // Keys
        template.setKeySerializer(new StringRedisSerializer());

        // Values
        template.setValueSerializer(serializer);

        template.afterPropertiesSet();

        return template;
    }
}