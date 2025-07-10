package com.microservice.paymentservice.helper;

import com.microservice.paymentservice.domain.Payment;
import com.microservice.paymentservice.dto.OrderDto;
import com.microservice.paymentservice.dto.PaymentDto;

public interface PaymentMappingHelper {
    public static PaymentDto map(final Payment payment){
        return PaymentDto.builder()
                .paymentId(payment.getPaymentId())
                .isPayed(payment.getIsPayed())
                .paymentStatus(payment.getPaymentStatus())
                .orderDto(
                        OrderDto.builder()
                                .orderId(payment.getOrderId())
                                .build()
                )
                .build();
    }

    public static Payment map(final PaymentDto paymentDto){
        return Payment.builder()
                .paymentId(paymentDto.getPaymentId())
                .orderId(paymentDto.getOrderDto().getOrderId())
                .isPayed(paymentDto.getIsPayed())
                .paymentStatus(paymentDto.getPaymentStatus())
                .build();
    }
}
