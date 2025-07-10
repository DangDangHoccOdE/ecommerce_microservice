package com.microservice.paymentservice.service.impl;

import com.microservice.paymentservice.config.client.OrderClient;
import com.microservice.paymentservice.dto.PaymentDto;
import com.microservice.paymentservice.exception.wrapper.PaymentNotFoundException;
import com.microservice.paymentservice.helper.PaymentMappingHelper;
import com.microservice.paymentservice.repository.PaymentRepository;
import com.microservice.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Qualifier;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;

    @Qualifier("")
    private final OrderClient orderClient;

    @Override
    public List<PaymentDto> findAll() {
        log.info("*** PaymentDto List, service; fetch all payments *");
        return this.paymentRepository.findAll()
                .stream()
                .map(PaymentMappingHelper::map)
                .map(p -> {
                    p.setOrderDto(this.orderClient.findById(p.getOrderDto().getOrderId()));
                    return p;
                })
                .distinct()
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public PaymentDto findById(Integer paymentId) {
        log.info("*** PaymentDto, service; fetch payment by id *");
        return this.paymentRepository.findById(paymentId)
                .map(PaymentMappingHelper::map)
                .map(p -> {
                    p.setOrderDto(this.orderClient.findById(p.getOrderDto().getOrderId()));
                    return p;
                })
                .orElseThrow(() -> new PaymentNotFoundException(String.format("Payment with id: %d not found", paymentId)));
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
        log.info("*** Void, service; delete payment by id *");
        this.paymentRepository.deleteById(paymentId);
    }
}
