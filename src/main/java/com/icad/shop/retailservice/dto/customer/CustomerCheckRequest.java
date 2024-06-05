package com.icad.shop.retailservice.dto.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerCheckRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = 3807950767331748183L;

    private String customerName;
    private String customerCode;
}
