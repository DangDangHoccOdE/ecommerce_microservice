package com.microservice.userservice.service.impl;

import com.microservice.userservice.dto.UserDto;
import com.microservice.userservice.exception.wrapper.UserObjectNotFoundException;
import com.microservice.userservice.helper.UserMappingHelper;
import com.microservice.userservice.repository.UserRepository;
import com.microservice.userservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDto> findAll() {
        log.info("*** UserDto List, service; fetch all users *");
        return this.userRepository.findAll()
                .stream()
                .map(UserMappingHelper::map)
                .distinct()
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public UserDto findById(Integer id) {
        log.info("*** UserDto, service; fetch user by id *");
        return this.userRepository.findById(id)
                .map(UserMappingHelper::map)
                .orElseThrow(() -> new UserObjectNotFoundException(String.format("User with id: %d not found", id)));
    }

    @Override
    public UserDto save(UserDto userDto) {
        log.info("**** UserDto, service; save user *");
        return UserMappingHelper.map(
                this.userRepository.save(UserMappingHelper.map(userDto))
        );
    }

    @Override
    public UserDto update(UserDto userDto) {
        log.info("*** UserDto, service; update user *");
        return UserMappingHelper.map(this.userRepository.save(UserMappingHelper.map(userDto)));
    }

    @Override
    public UserDto update(Integer userId, UserDto userDto) {
        log.info("*** UserDto, service; update user with userId *");
        return UserMappingHelper.map(this.userRepository.save(UserMappingHelper.map(this.findById(userId))));
    }

    @Override
    public void deleteById(Integer userId) {
        log.info("*** Void, service; delete user by id *");
        this.userRepository.deleteById(userId);
    }

    @Override
    public UserDto findByUsername(String username) {
        log.info("*** UserDto, service; fetch user with username *");
        return UserMappingHelper.map(this.userRepository.findByCredentialUsername(username)
                .orElseThrow(() -> new UserObjectNotFoundException(String.format("User with username: %s not found", username))));
    }
}
