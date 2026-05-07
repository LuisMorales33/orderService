# Order Service - Hexagonal Architecture

Backend API for order management built with **Spring Boot** following **Hexagonal Architecture (Ports & Adapters)**.

Designed to be scalable and evolve into a microservices ecosystem.

---

## Overview

This project demonstrates how to build a clean, maintainable, and scalable backend service using modern backend practices:

- Separation of concerns (Domain, Application, Infrastructure)
- Decoupled architecture using ports and adapters
- DTO validation and clean API design
- Centralized error handling
- Cache integration with Redis (cache-aside pattern)

---

## Tech Stack

- Java 17+
- Spring Boot
- Spring Data JPA
- MySQL
- Redis
- Docker (optional)
- Maven

---

## Architecture

Hexagonal Architecture (Ports & Adapters):

Controller -> UseCase -> Port -> Adapter -> Database / External systems

---

### Layers:

- **Domain** - Business logic (Order, OrderStatus, Ports)
- **Application** - Use cases (Create, Get, Update)
- **Infrastructure** - JPA, Redis adapters
- **EntryPoints** - REST Controllers

---

## Features

- Create orders
- Get order by ID
- Update order status
- Input validation with DTOs
- Global exception handling
- Redis caching (read optimization)

---

## Example Flow

POST /orders  >  DTO validation  >  UseCase execution  >  Persistence (MySQL)  >  Response mapping

---

## Caching Strategy

Implements **Cache Aside Pattern**:

- First read > DB > stored in Redis
- Subsequent reads > Redis (faster)
- Updates > cache invalidation

---

## Getting Started

### 1. Clone the repository
    git clone https://github.com/LuisMorales33/order-service.git
    cd order-service

### 2. Run MySQL (Docker)
    docker run -d \
      --name mysql-orders \
      -e MYSQL_ROOT_PASSWORD=root \
      -e MYSQL_DATABASE=order_db \
      -p 3306:3306 \
      mysql:8
      
### 3. Run Redis

    docker run -d \
      --name redis-orders \
      -p 6379:6379 \
      redis
  
### 4. Run the application

    ./mvnw spring-boot:run

---

### API Endpoints

| Method | Endpoint            | Description         |
| ------ | ------------------- | ------------------- |
| POST   | /orders             | Create order        |
| GET    | /orders/{id}        | Get order by ID     |
| PUT    | /orders/{id}/status | Update order status |



### Sample Request POST /api/orders/orders
    
    {
      "userId": 1,
      "totalPrice": 150.0
    }

  ### Sample Response

    {
      "message": "Order created successfully",
      "data": {
        "id": 1,
        "userId": 1,
        "status": "CREATED",
        "totalPrice": 150.0,
        "createdAt": "2026-05-06T10:00:00"
      },
      "error": null
    }


### Error Handling

Centralized error handling using @RestControllerAdvice.
Returns consistent error responses:

    {
      "message": "Validation error",
      "data": null,
      "error": "totalPrice must be greater than 0"
    }

---

### Author

Luis Morales





