package com.store.dto.customer;

import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@SuperBuilder
public class PrivateCustomerDTO extends CustomerDTO {}
