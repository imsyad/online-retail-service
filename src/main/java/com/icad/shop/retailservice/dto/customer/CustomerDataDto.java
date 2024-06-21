package com.icad.shop.retailservice.dto.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDataDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 2225590307676500405L;

    private Long customerId;
    private String customerName;
    private String customerAddress;
    private String customerCode;
    private String customerPhone;
    private String pic;
    private LocalDate lastOrderDate;
    private Boolean isActive;
}
