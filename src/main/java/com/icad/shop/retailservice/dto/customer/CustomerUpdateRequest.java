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
    private static final long serialVersionUID = 7064491483894842576L;

    private String customerName;
    private String customerAddress;
    private String customerCode;
    private String customerPhone;
    private String pic;
}
