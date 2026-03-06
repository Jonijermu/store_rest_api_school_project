package com.store.dto.supplier;


import com.store.entity.SupplierAddress;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SupplierDTO {

    private Integer id;
    private String name;
    private String contactName;
    private String phone;
    private String email;
    private List<SupplierAddressesDTO> addresses;

}
