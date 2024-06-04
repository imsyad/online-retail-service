package com.icad.shop.retailservice.dto.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerListRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = -606867853688800466L;

    private String startDate;
    private String endDate;
    private String sortBy;
    private String sortDir;
}
