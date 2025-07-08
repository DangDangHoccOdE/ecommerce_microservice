package com.microservice.orderservice.config.client.fallback;

import com.microservice.orderservice.config.client.UserClient;
import com.microservice.orderservice.dto.UserDto;
import org.springframework.stereotype.Component;

import java.util.Set;


@Component
public class UserClientFallback implements UserClient {

    @Override
    public UserDto findById(Integer id) {
        return UserDto.builder()
                .userId(id)
                .firstName("Guest")
                .lastName("#" + id)
                .email("guest" + id + "@example.com")
                .phone("N/A")
                .imageUrl("https://cdn-icons-png.flaticon.com/512/847/847969.png") // avatar mặc định
                .cartDto(null)
                .build();
    }
}