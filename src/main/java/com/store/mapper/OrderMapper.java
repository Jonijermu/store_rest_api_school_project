package com.store.mapper;

import com.store.dto.order.CustomerOrdersDTO;
import com.store.dto.order.OrderDTO;
import com.store.entity.Customer;
import com.store.entity.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {


    private final AddressMapper addressMapper;

    public OrderMapper(
            AddressMapper addressMapper
    ) {
        this.addressMapper =addressMapper;
    }


    public CustomerOrdersDTO toDto(Customer customer, List<Order> orders) {
        return CustomerOrdersDTO.builder()
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .phone(customer.getPhone())
                .orders(orders.stream()
                        .map(this::toDto)
                        .collect(Collectors.toList())
                )
                .build();
    }

    public OrderDTO toDto(Order order) {
        return OrderDTO.builder()
                .id(order.getId())
                .orderDate(order.getOrderDate())
                .deliveryDate(order.getDeliveryDate())
                .shippingAddress(addressMapper.toDto(order.getShippingAddress()))
                .status(order.getStatus())
                .build();
    }
}
