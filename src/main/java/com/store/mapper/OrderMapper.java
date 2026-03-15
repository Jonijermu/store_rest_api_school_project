package com.store.mapper;

import com.store.dto.customer.CompanyCustomerDTO;
import com.store.dto.customer.PrivateCustomerDTO;
import com.store.dto.order.CompanyCustomerOrdersDTO;
import com.store.dto.order.CustomerOrdersDTO;
import com.store.dto.order.OrderDTO;
import com.store.dto.order.PrivateCustomerOrdersDTO;
import com.store.entity.CompanyCustomer;
import com.store.entity.Customer;
import com.store.entity.Order;
import com.store.entity.PrivateCustomer;
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


    public PrivateCustomerOrdersDTO toPrivateCustomerOrderDto(PrivateCustomer customer, List<Order> orders) {
        PrivateCustomerDTO privateCustomerDto = PrivateCustomerDTO.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .phone(customer.getPhone())
                .build();


        return PrivateCustomerOrdersDTO.builder()
                .privateCustomer(privateCustomerDto)
                .orders(orders.stream()
                        .map(this::toOrderDto)
                        .collect(Collectors.toList())
                )
                .build();
    }

    public CompanyCustomerOrdersDTO toCompanyCustomerOrderDto(CompanyCustomer customer, List<Order> orders) {
        CompanyCustomerDTO companyCustomerDTO = CompanyCustomerDTO.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .phone(customer.getPhone())
                .company(customer.getCompanyName())
                .billingEmail(customer.getEmail())
                .build();

        return CompanyCustomerOrdersDTO.builder()
                .companyCustomer(companyCustomerDTO)
                .orders(orders.stream()
                        .map(this::toOrderDto)
                        .collect(Collectors.toList())
                )
                .build();
    }

    public OrderDTO toOrderDto(Order order) {
        return OrderDTO.builder()
                .id(order.getId())
                .orderDate(order.getOrderDate())
                .deliveryDate(order.getDeliveryDate())
                .shippingAddress(addressMapper.toDto(order.getShippingAddress()))
                .status(order.getStatus())
                .build();
    }
}
