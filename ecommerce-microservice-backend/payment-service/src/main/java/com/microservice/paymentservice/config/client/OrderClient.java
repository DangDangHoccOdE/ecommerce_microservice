package com.microservice.paymentservice.config.client;

import com.microservice.paymentservice.config.client.fallback.OrderClientFallback;
import com.microservice.paymentservice.dto.OrderDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ORDER-SERVICE", path = "/order-service/api/orders",fallback = OrderClientFallback.class)
public interface OrderClient {
    @GetMapping("/{id}")
    OrderDto findById(@PathVariable("id") Integer id);
}
