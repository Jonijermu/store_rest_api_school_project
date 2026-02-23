package com.store.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@Embeddable
public class OrderItemId implements Serializable {
    private static final long serialVersionUID = -5946810776152830152L;
    @Column(name = "order_id", nullable = false)
    private int orderId;

    @Column(name = "product_id", nullable = false)
    private int productId;


}