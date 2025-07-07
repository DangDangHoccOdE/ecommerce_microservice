package com.microservice.favouriteservice.config.client.fallback;

import com.microservice.favouriteservice.config.client.ProductClient;
import com.microservice.favouriteservice.dto.ProductDto;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class ProductClientFallback implements ProductClient {
    @Override
    public ProductDto findById(Integer id) {
        return ProductDto.builder()
                .productId(id)
                .productTitle("Unknown Product")
                .sku("UNKNOWN-SKU")
                .priceUnit(0.0)
                .quantity(0)
                .imageUrl("https://cdn-icons-png.flaticon.com/512/2748/2748558.png") // ảnh sản phẩm tạm
                .favouriteDtos(Set.of()) // hoặc null nếu bạn thích
                .build();
    }
}
