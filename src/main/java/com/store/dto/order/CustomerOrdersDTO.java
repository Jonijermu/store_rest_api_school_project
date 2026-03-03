package com.store.dto.order;

import com.store.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerOrdersDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private List<OrderDTO> orders;
}
