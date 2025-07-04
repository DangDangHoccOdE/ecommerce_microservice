package com.microservice.userservice.repository;

import com.microservice.userservice.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByCredentialUsername(final String credentialUserName);
}
