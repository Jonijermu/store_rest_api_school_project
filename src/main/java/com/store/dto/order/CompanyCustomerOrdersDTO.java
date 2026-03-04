package com.store.dto.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.store.dto.customer.CompanyCustomerDTO;
import lombok.*;

import java.util.List;


@JsonPropertyOrder({ "companyCustomer", "orders" })
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyCustomerOrdersDTO extends CustomerOrdersDTO{

    @JsonProperty("companyCustomer")
    private CompanyCustomerDTO companyCustomerDTO;
    private List<OrderDTO> orders;
}
