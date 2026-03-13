package com.store.mapper;


import com.store.dto.orderitem.OrderItemDTO;
import com.store.entity.OrderItem;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class OrderItemMapper {

    private final ProductMapper productMapper;

    public List<OrderItemDTO> toOrderItemDtoList(List<OrderItem> items) {
        return items.stream()
                .map(this::toOrderItemDto)
                .collect(Collectors.toList());
    }

    private OrderItemDTO toOrderItemDto(OrderItem orderItem) {
        return OrderItemDTO.builder()
                .productId(orderItem.getProduct().getId())
                .quantity(orderItem.getQuantity())
                .unitPrice(orderItem.getUnitPrice()
                        .multiply(BigDecimal.valueOf(orderItem.getQuantity()))
                )
                .productDTO(productMapper.toProductDto(orderItem.getProduct()))
                .build();
    }
}
