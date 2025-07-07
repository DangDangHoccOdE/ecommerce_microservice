package com.microservice.favouriteservice.config.client;

import com.microservice.favouriteservice.config.client.fallback.ProductClientFallback;
import com.microservice.favouriteservice.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "PRODUCT-SERVICE", path = "/product-service/api/products", fallback = ProductClientFallback.class)
public interface ProductClient {
    @GetMapping("/{id}")
    ProductDto findById(@PathVariable("id") Integer id);
}
