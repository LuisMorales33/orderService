package com.practice.orderService.infrastructure.adapters.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaOrderRepository extends JpaRepository<OrderEntity, Long> {

}
