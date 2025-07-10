package com.microservice.paymentservice.repository;

import com.microservice.paymentservice.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}
