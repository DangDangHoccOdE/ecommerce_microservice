package com.microservice.favouriteservice.service.impl;

import com.microservice.favouriteservice.config.client.ProductClient;
import com.microservice.favouriteservice.config.client.UserClient;
import com.microservice.favouriteservice.domain.id.FavouriteId;
import com.microservice.favouriteservice.dto.FavouriteDto;
import com.microservice.favouriteservice.exception.wrapper.FavouriteNotFoundException;
import com.microservice.favouriteservice.helper.FavouriteMappingHelper;
import com.microservice.favouriteservice.repository.FavouriteRepository;
import com.microservice.favouriteservice.service.FavouriteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class FavouriteServiceImpl implements FavouriteService {
    private final FavouriteRepository favouriteRepository;

    @Qualifier("com.microservice.favouriteservice.config.client.ProductClient")
    private final ProductClient productClient;

    @Qualifier("com.microservice.favouriteservice.config.client.UserClient")
    private final UserClient userClient;

    @Override
    public List<FavouriteDto> findAll() {
        log.info("*** FavouriteDto List, service; fetch all favourites *");
        return this.favouriteRepository.findAll()
                .stream()
                .map(FavouriteMappingHelper::map)
                .map(f -> {
                    f.setUserDto(userClient.findById(f.getUserId()));
                    f.setProductDto(productClient.findById(f.getProductId()));
                    return f;
                })
                .distinct()
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public FavouriteDto findById(FavouriteId favouriteId) {
        log.info("*** FavouriteDto, service; fetch favourite by id *");
        return this.favouriteRepository.findById(favouriteId)
                .map(FavouriteMappingHelper::map)
                .map(f -> {
                    f.setUserDto(userClient.findById(f.getUserId()));
                    f.setProductDto(productClient.findById(f.getProductId()));
                    return f;
                })
                .orElseThrow(() -> new FavouriteNotFoundException(
                        String.format("Favourite with id: [%s] not found!", favouriteId)
                ));
    }

    @Override
    public FavouriteDto save(final FavouriteDto favouriteDto) {
        return FavouriteMappingHelper.map(this.favouriteRepository
                .save(FavouriteMappingHelper.map(favouriteDto)));
    }

    @Override
    public FavouriteDto update(FavouriteDto favouriteDto) {
        return FavouriteMappingHelper.map(this.favouriteRepository
                .save(FavouriteMappingHelper.map(favouriteDto)));
    }

    @Override
    public void deleteById(FavouriteId favouriteId) {
        this.favouriteRepository.deleteById(favouriteId);
    }
}
