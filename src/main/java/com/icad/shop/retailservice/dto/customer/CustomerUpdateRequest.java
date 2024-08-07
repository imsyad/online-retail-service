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
public class CustomerUpdateRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = -8745032464998052025L;

    private Long customerId;
    private String customerName;
    private String customerAddress;
    private String customerCode;
    private String customerPhone;
    private String pic;
}
