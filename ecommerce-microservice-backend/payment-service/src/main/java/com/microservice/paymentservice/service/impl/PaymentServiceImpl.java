package com.microservice.paymentservice.service.impl;

import com.microservice.paymentservice.dto.PaymentDto;
import com.microservice.paymentservice.helper.PaymentMappingHelper;
import com.microservice.paymentservice.repository.PaymentRepository;
import com.microservice.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;

    @Override
    public List<PaymentDto> findAll() {
        return List.of();
    }

    @Override
    public PaymentDto findById(Integer paymentId) {
        return null;
    }

    @Override
    public PaymentDto save(PaymentDto paymentDto) {
        log.info("*** PaymentDto, service; save payment *");
        return PaymentMappingHelper.map(this.paymentRepository
                .save(PaymentMappingHelper.map(paymentDto)));
    }

    @Override
    public PaymentDto update(PaymentDto paymentDto) {
        log.info("** PaymentDto, service; update payment *");
        return PaymentMappingHelper.map(this.paymentRepository.save(PaymentMappingHelper.map(paymentDto)));
    }

    @Override
    public void deleteById(Integer paymentId) {

    }
}
