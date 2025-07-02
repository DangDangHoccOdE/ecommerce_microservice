package com.microservice.userservice.service;

import com.microservice.userservice.domain.User;
import com.microservice.userservice.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> findAll();
    UserDto findById(final Integer id);
    UserDto save(final UserDto userDto);
    UserDto update(final UserDto userDto);
    UserDto update(final Integer userId, final UserDto userDto);
    void deleteById(final Integer userId);
    UserDto findByUsername(final String username);
}
