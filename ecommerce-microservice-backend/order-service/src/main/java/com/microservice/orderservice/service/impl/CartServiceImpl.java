package com.microservice.orderservice.service.impl;

import com.microservice.orderservice.config.client.UserClient;
import com.microservice.orderservice.dto.CartDto;
import com.microservice.orderservice.exception.wrapper.CartNotFoundException;
import com.microservice.orderservice.helper.CartMappingHelper;
import com.microservice.orderservice.repository.CartRepository;
import com.microservice.orderservice.service.CartService;
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
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final UserClient userClient;
    @Override
    public List<CartDto> findAll() {
        log.info("*** CartDto List, service; fetch all carts *");
        return this.cartRepository.findAll()
                .stream()
                .map(CartMappingHelper::map)
                .map(c -> {
                    c.setUserDto(userClient.findById(c.getUserDto().getUserId()));
                    return c;
                })
                .distinct()
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public CartDto findById(final Integer cartId) {
        log.info("*** CartDto, service; fetch cart by id *");
        return this.cartRepository.findById(cartId)
                .map(CartMappingHelper::map)
                .map(c -> {
                    c.setUserDto(userClient.findById(c.getUserDto().getUserId()));
                    return c;
                })
                .orElseThrow(() ->
                    new CartNotFoundException(String.format("Cart with id %d not found", cartId))
                );
    }

    @Override
    public CartDto save(CartDto cartDto) {
        log.info("*** CartDto, service; save cart *");
        return CartMappingHelper.map(this.cartRepository.save(CartMappingHelper.map(cartDto)));
    }

    @Override
    public CartDto update(CartDto cartDto) {
        log.info("*** CartDto, service; update cart *");
        return CartMappingHelper.map(this.cartRepository.save(CartMappingHelper.map(cartDto)));
    }

    @Override
    public CartDto update(Integer cartId, CartDto cartDto) {
        log.info("*** CartDto, service; update cart with cartId *");
        return CartMappingHelper.map(this.cartRepository.save(CartMappingHelper.map(this.findById(cartId))));
    }

    @Override
    public void deleteById(Integer cartId) {
        log.info("*** Void, service; delete cart by id *");
        this.cartRepository.deleteById(cartId);
    }
}
