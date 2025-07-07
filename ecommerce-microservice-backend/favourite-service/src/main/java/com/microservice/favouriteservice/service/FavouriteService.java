package com.microservice.favouriteservice.service;

import com.microservice.favouriteservice.domain.Favourite;
import com.microservice.favouriteservice.domain.id.FavouriteId;
import com.microservice.favouriteservice.dto.FavouriteDto;

import java.util.List;

public interface FavouriteService {
    List<FavouriteDto> findAll();
    FavouriteDto findById(final FavouriteId favouriteId);
    FavouriteDto save(final FavouriteDto favouriteDto);
    FavouriteDto update(final FavouriteDto favouriteDto);
    void deleteById(final FavouriteId favouriteId);
}
