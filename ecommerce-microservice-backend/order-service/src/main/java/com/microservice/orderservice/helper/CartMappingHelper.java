package com.microservice.orderservice.helper;

import com.microservice.orderservice.domain.Cart;
import com.microservice.orderservice.dto.CartDto;
import com.microservice.orderservice.dto.UserDto;

public interface CartMappingHelper {
    public static CartDto map(final Cart cart){
        return CartDto.builder()
                .cartId(cart.getCartId())
                .userId(cart.getUserId())
                .userDto(
                        UserDto.builder()
                                .userId(cart.getUserId())
                                .build()
                )
                .build();
    }

    public static Cart map(final CartDto cartDto){
        return Cart.builder()
                .cartId(cartDto.getCartId())
                .userId(cartDto.getUserId())
                .build();
    }
}
