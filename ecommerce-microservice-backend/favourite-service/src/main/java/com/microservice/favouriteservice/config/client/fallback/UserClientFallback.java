package com.microservice.favouriteservice.config.client.fallback;

import com.microservice.favouriteservice.config.client.UserClient;
import com.microservice.favouriteservice.dto.UserDto;
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
                .favouriteDtos(Set.of())
                .build();
    }
}
