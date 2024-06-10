package com.icad.shop.retailservice.dto.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreateRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = -4371376844935064462L;

    private long customerCode;
    private long itemCode;
    private BigDecimal quantity;
}
