package com.microservice.orderservice.config.client;

import com.microservice.orderservice.config.client.fallback.UserClientFallback;
import com.microservice.orderservice.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "USER-SERVICE", path = "/user-service/api/users",fallback = com.microservice.orderservice.config.client.fallback.UserClientFallback.class)
public interface UserClient {
    @GetMapping("/{id}")
    com.microservice.orderservice.dto.UserDto findById(@PathVariable("id") Integer id);
}

