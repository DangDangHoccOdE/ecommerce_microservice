package com.microservice.shippingservice.config.client.fallback;

import com.microservice.shippingservice.config.client.ProductClient;
import com.microservice.shippingservice.dto.ProductDto;
import org.springframework.stereotype.Component;

@Component
public class ProductClientFallback implements ProductClient {
    @Override
    public ProductDto findById(Integer id) {
        return ProductDto.builder()
                .productId(0)
                .productTitle("Default Title")
                .imageUrl("https://example.com/default.jpg")
                .sku("DEFAULT-SKU")
                .price(0.0)
                .quantity(0)
                .orderItemsItemDtos(null)
                .build();
    }
}
