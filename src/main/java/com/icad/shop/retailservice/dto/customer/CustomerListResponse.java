package com.icad.shop.retailservice.dto.customer;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerListResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = -7937173517549834542L;

    private List<CustomerDataDto> customerList;

}
