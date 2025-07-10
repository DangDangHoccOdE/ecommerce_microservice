package com.microservice.shippingservice.helper;

import com.microservice.shippingservice.domain.OrderItem;
import com.microservice.shippingservice.dto.OrderDto;
import com.microservice.shippingservice.dto.OrderItemDto;
import com.microservice.shippingservice.dto.ProductDto;

public class OrderItemMappingHelper {
    public static OrderItemDto map(final OrderItem orderItem){
        return OrderItemDto.builder()
                .productId(orderItem.getProductId())
                .orderId(orderItem.getOrderId())
                .orderedQuantity(orderItem.getOrderQuantity())
                .productDto(
                        ProductDto.builder()
                                .productId(orderItem.getProductId())
                                .build()
                )
                .orderDto(
                        OrderDto.builder()
                                .orderId(orderItem.getOrderId())
                                .build()
                )
                .build();
    }

    public static OrderItem map(final OrderItemDto orderItemDto) {
        return OrderItem.builder()
                .productId(orderItemDto.getProductId())
                .orderId(orderItemDto.getOrderId())
                .orderQuantity(orderItemDto.getOrderedQuantity())
                .build();
    }
}
