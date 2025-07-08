package com.microservice.orderservice.repository;

import com.microservice.orderservice.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Integer> {
}
