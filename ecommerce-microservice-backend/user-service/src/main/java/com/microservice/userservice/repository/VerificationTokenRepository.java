package com.microservice.userservice.repository;

import com.microservice.userservice.domain.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Integer> {

}
