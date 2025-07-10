package com.microservice.shippingservice.service.impl;

import com.microservice.shippingservice.config.client.OrderClient;
import com.microservice.shippingservice.config.client.ProductClient;
import com.microservice.shippingservice.domain.id.OrderItemId;
import com.microservice.shippingservice.dto.OrderItemDto;
import com.microservice.shippingservice.exception.wrapper.OrderItemNotFoundException;
import com.microservice.shippingservice.helper.OrderItemMappingHelper;
import com.microservice.shippingservice.repository.OrderItemRepository;
import com.microservice.shippingservice.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {
    private final OrderItemRepository orderItemRepository;
    @Qualifier("com.microservice.shippingservice.config.client.OrderClient")
    private final OrderClient orderClient;
    @Qualifier("com.microservice.shippingservice.config.client.ProductClient")
    private final ProductClient productClient;

    @Override
    public List<OrderItemDto> findAll() {
        log.info("*** OrderItemDto List, service; fetch all orderItems *");
        return this.orderItemRepository.findAll()
                .stream()
                .map(OrderItemMappingHelper::map)
                .map(o -> {
                    o.setProductDto(this.productClient.findById(o.getProductId()));
                    o.setOrderDto(this.orderClient.findById(o.getOrderId()));
                    return o;
                })
                .distinct()
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public OrderItemDto findById(OrderItemId orderItemId) {
        log.info("*** OrderItemDto, service; fetch orderItem by id *");
        return this.orderItemRepository.findById(null)
                .map(OrderItemMappingHelper::map)
                .map(o -> {
                    o.setProductDto(this.productClient.findById(o.getProductDto().getProductId()));
                    o.setOrderDto(this.orderClient.findById(o.getOrderDto().getOrderId()));
                    return o;
                })
                .orElseThrow(() -> new OrderItemNotFoundException(String.format("OrderItem with id: %s not found", orderItemId)));
    }

    @Override
    public OrderItemDto save(OrderItemDto orderItemDto) {
        log.info("*** OrderItemDto, service; save OrderItem *");
        return OrderItemMappingHelper.map(this.orderItemRepository.save(
                OrderItemMappingHelper.map(orderItemDto)
        ));
    }

    @Override
    public OrderItemDto update(OrderItemDto orderItemDto) {
        log.info("*** OrderItemDto, service; update OrderItem *");
        return OrderItemMappingHelper.map(this.orderItemRepository
            .save(OrderItemMappingHelper.map(orderItemDto)));
    }

    @Override
    public void deleteById(OrderItemId orderItemId) {
        log.info("*** Void, service; delete orderItem by id *");
        this.orderItemRepository.deleteById(orderItemId);
    }
}
