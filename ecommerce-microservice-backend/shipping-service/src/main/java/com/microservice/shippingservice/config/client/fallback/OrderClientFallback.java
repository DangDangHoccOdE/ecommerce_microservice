package com.microservice.shippingservice.config.client.fallback;

import com.microservice.shippingservice.config.client.OrderClient;
import com.microservice.shippingservice.dto.OrderDto;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
@Component
public class OrderClientFallback implements OrderClient {
    @Override
    public OrderDto findById(Integer id) {
        return OrderDto.builder()
                .orderId(0)
                .orderDate(LocalDateTime.now())
                .orderDesc("Default description")
                .orderFee(0.0)
                .build();
    }
}
