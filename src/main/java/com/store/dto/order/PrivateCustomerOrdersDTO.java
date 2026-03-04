package com.store.dto.order;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.store.dto.customer.PrivateCustomerDTO;
import lombok.*;

import java.util.List;


@JsonPropertyOrder({ "privateCustomer", "orders" })
@EqualsAndHashCode(callSuper = true)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrivateCustomerOrdersDTO extends CustomerOrdersDTO{


    private PrivateCustomerDTO privateCustomerDTO;
    private List<OrderDTO> orders;
}
