package com.microservice.orderservice.resource;

import com.microservice.orderservice.dto.CartDto;
import com.microservice.orderservice.dto.response.collection.DtoCollectionResponse;
import com.microservice.orderservice.helper.CartMappingHelper;
import com.microservice.orderservice.service.CartService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carts")
@Slf4j
@RequiredArgsConstructor
public class CartResource {
    private final CartService cartService;

    @GetMapping
    public ResponseEntity<DtoCollectionResponse<CartDto>> findAll(){
        log.info("*** CartDto List, controller; fetch all categories *");
        return ResponseEntity.ok(new DtoCollectionResponse<>(this.cartService.findAll()));
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<CartDto> findById(
            @PathVariable("cartId")
            @NotBlank(message = "Input must not be blank")
            @Valid final String cartId
    ){
        log.info("*** CartDto, resource; fetch cart by id *");
        return ResponseEntity.ok(this.cartService.findById(Integer.parseInt(cartId)));
    }

    @PostMapping
    public ResponseEntity<CartDto> save(
            @RequestBody
            @NotNull(message = "Input must be not NULL")
            @Valid final CartDto cartDto
    ){
        log.info("*** CartDto, resource; save cart *");
        return ResponseEntity.ok(this.cartService.save(cartDto));
    }

    @PutMapping
    public ResponseEntity<CartDto> update(
            @RequestBody
            @NotNull(message = "Input must not be NULL")
            @Valid final CartDto cartDto
    ){
        log.info("*** CartDto, resource; update cart *");
        return ResponseEntity.ok(this.cartService.update(cartDto));
    }

    @PutMapping("/{cardId}")
    public ResponseEntity<CartDto> update(
            @PathVariable("cartId")
            @NotBlank(message = "Input must not be NULL")
            @Valid final String cardId,
            @RequestBody
            @NotNull(message = "Input must not be NULL")
            @Valid final CartDto cartDto
    ){
        log.info("*** CartDto, resource; update cart with cartId *");
        return ResponseEntity.ok(this.cartService.update(Integer.parseInt(cardId),cartDto));
    }

    @DeleteMapping("/{cartId}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("cartId") final String cartId) {
        log.info("*** Boolean, resource; delete cart by id *");
        this.cartService.deleteById(Integer.parseInt(cartId));
        return ResponseEntity.ok(true);
    }
}
