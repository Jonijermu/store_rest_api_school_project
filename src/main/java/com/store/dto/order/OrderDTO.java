package com.store.dto.order;

import com.store.dto.customer.CustomerAddressDTO;
import com.store.utils.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDTO {

    private Integer id;
    private Date orderDate;
    private Date deliveryDate;
    private CustomerAddressDTO shippingAddress;
    private OrderStatus status;
}
