package com.store.dto.order;

import com.store.dto.customer.CompanyCustomerDTO;
import lombok.*;

import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyCustomerOrdersDTO extends CustomerOrdersDTO{

    private CompanyCustomerDTO companyCustomerDTO;
    private List<OrderDTO> orders;
}
