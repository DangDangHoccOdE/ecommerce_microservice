package com.microservice.shippingservice.repository;

import com.microservice.shippingservice.domain.OrderItem;
import com.microservice.shippingservice.domain.id.OrderItemId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemId> {
}
