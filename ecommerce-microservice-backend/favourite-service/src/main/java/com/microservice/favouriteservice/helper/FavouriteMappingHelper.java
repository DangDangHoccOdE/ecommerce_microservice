package com.microservice.favouriteservice.helper;

import com.microservice.favouriteservice.domain.Favourite;
import com.microservice.favouriteservice.dto.FavouriteDto;
import com.microservice.favouriteservice.dto.ProductDto;
import com.microservice.favouriteservice.dto.UserDto;

public interface FavouriteMappingHelper {
    public static FavouriteDto map(final Favourite favourite) {
        return FavouriteDto.builder()
                .userId(favourite.getUserId())
                .productId(favourite.getProductId())
                .likeDate(favourite.getLikeDate())
                .userDto(
                        UserDto.builder()
                                .userId(favourite.getUserId())
                                .build()
                )
                .productDto(
                        ProductDto.builder()
                                .productId(favourite.getProductId())
                                .build()
                )
                .build();
    }

    public static Favourite map(final FavouriteDto favouriteDto) {
        return Favourite.builder()
                .userId(favouriteDto.getUserId())
                .productId(favouriteDto.getProductId())
                .likeDate(favouriteDto.getLikeDate())
                .build();
    }
}
