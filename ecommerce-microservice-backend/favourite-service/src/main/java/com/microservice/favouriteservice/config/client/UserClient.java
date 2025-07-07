package com.microservice.favouriteservice.config.client;

import com.microservice.favouriteservice.config.client.fallback.UserClientFallback;
import com.microservice.favouriteservice.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "USER-SERVICE", path = "/user-service/api/users",fallback = UserClientFallback.class)
public interface UserClient {
    @GetMapping("/{id}")
    UserDto findById(@PathVariable("id") Integer id);
}
