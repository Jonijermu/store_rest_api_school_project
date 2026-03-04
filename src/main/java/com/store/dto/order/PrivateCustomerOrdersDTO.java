package com.store.dto.order;

import com.store.dto.customer.PrivateCustomerDTO;
import lombok.*;

import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrivateCustomerOrdersDTO extends CustomerOrdersDTO{

    private PrivateCustomerDTO privateCustomerDTO;
    private List<OrderDTO> orders;
}
