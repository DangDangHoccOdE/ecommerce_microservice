package com.microservice.shippingservice.domain;

import com.microservice.shippingservice.domain.id.OrderItemId;
import jakarta.persistence.*;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "order_items")
@IdClass(OrderItemId.class)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class OrderItem extends AbstractMappedEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "product_id", nullable = false, updatable = false)
    private Integer productId;

    @Id
    @Column(name = "order_id", nullable = false, updatable = false)
    private Integer orderId;

    @Column(name = "order_quantity")
    private Integer orderQuantity;
}
