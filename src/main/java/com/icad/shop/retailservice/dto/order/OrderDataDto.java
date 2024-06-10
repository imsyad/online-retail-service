package com.icad.shop.retailservice.dto.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDataDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 8871746900052128619L;

    private long orderId;
    private String orderCode;
    private LocalDate orderDate;
    private BigDecimal quantity;
    private BigDecimal totalPrice;
    private String itemName;
    private String itemCode;
    private String customerName;
    private String customerCode;
}
