package com.microservice.orderservice.resource;

import com.microservice.orderservice.dto.OrderDto;
import com.microservice.orderservice.dto.response.collection.DtoCollectionResponse;
import com.microservice.orderservice.service.OrderService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@Slf4j
@RequiredArgsConstructor
public class OrderResource {
    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<DtoCollectionResponse<OrderDto>> findAll(){
        log.info("*** OrderDto List, controller; fetch all orders *");
        return ResponseEntity.ok(new DtoCollectionResponse<>(orderService.findAll()));
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDto> findById(
            @PathVariable("orderId")
            @NotBlank(message = "Input must not be blank")
            @Valid final String orderId
    ){
        log.info("*** OrderDto, resource; fetch order by id *");
        return ResponseEntity.ok(this.orderService.findById(Integer.parseInt(orderId)));
    }

    @PostMapping
    public ResponseEntity<OrderDto> save(
            @RequestBody
            @NotNull(message = "Input must not be NULL")
            @Valid final OrderDto orderDto
    ){
        log.info("*** OrderDto, resource; save order *");
        return ResponseEntity.ok(orderService.save(orderDto));
    }

    @PutMapping
    public ResponseEntity<OrderDto> update(
            @RequestBody
            @NotNull(message = "Input must not be NULL")
            @Valid final OrderDto orderDto
    ){
        log.info("**** OrderDto, resource; update order *");
        return ResponseEntity.ok(this.orderService.update(orderDto));
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<OrderDto> update(
            @PathVariable("orderId")
            @NotBlank(message = "Input must not be blank")
            @Valid final String orderId,
            @RequestBody
            @NotNull(message = "Input must not be NULL")
            @Valid final OrderDto orderDto
    ){
        log.info("*** OrderDto, resource; update order with orderId *");
        return ResponseEntity.ok(orderService.update(Integer.parseInt(orderId), orderDto));
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("orderId") final String orderId){
        log.info("*** Boolean, resource; delete order by id *");
        this.orderService.deleteById(Integer.parseInt(orderId));
        return ResponseEntity.ok(true);
    }
}
