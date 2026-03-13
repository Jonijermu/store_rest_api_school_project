package com.store.dto.orderitem;

import com.store.dto.product.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDTO {

    private Integer productId;
    private int quantity;
    private BigDecimal unitPrice;
    private ProductDTO productDTO;
}
