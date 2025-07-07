package com.microservice.favouriteservice.repository;

import com.microservice.favouriteservice.domain.Favourite;
import com.microservice.favouriteservice.domain.id.FavouriteId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavouriteRepository extends JpaRepository<Favourite, FavouriteId> {
}
