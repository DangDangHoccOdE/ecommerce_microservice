package com.microservice.orderservice.service.impl;

import com.microservice.orderservice.dto.OrderDto;
import com.microservice.orderservice.exception.wrapper.OrderNotFoundException;
import com.microservice.orderservice.helper.OrderMappingHelper;
import com.microservice.orderservice.repository.OrderRepository;
import com.microservice.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    @Override
    public List<OrderDto> findAll() {
        log.info("*** OrderDto List, service; fetch all orders *");
        return this.orderRepository.findAll()
                .stream()
                .map(OrderMappingHelper::map)
                .distinct()
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public OrderDto findById(final Integer orderId) {
        log.info("*** OrderDto, service; fetch order by id *");
        return this.orderRepository.findById(orderId)
                .map(OrderMappingHelper::map)
                .orElseThrow(() -> new OrderNotFoundException(String.format("Order with id: %d not found", orderId)));
    }

    @Override
    public OrderDto save(OrderDto orderDto) {
        log.info("*** OrderDto, service; save order *");
        return OrderMappingHelper.map(this.orderRepository.save(OrderMappingHelper.map(orderDto)));
    }

    @Override
    public OrderDto update(OrderDto orderDto) {
        log.info("*** OrderDto, service; update order *");
        return OrderMappingHelper.map(this.orderRepository
                .save(OrderMappingHelper.map(orderDto)));
    }

    @Override
    public OrderDto update(Integer orderId, OrderDto orderDto) {
        log.info("*** OrderDto, service; update order with orderId *");
        return OrderMappingHelper.map(this.orderRepository.save(OrderMappingHelper.map(this.findById(orderId))));
    }

    @Override
    public void deleteById(Integer orderId) {
        log.info("*** Void, service; delete order by id *");
        this.orderRepository.delete(OrderMappingHelper.map(this.findById(orderId)));
    }
}
