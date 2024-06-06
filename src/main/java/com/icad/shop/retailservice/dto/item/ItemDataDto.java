package com.icad.shop.retailservice.dto.item;

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
public class ItemDataDto implements Serializable {
    @Serial
    private static final long serialVersionUID = -5274861163945559197L;

    private long itemsId;
    private String itemName;
    private String itemsCode;
    private Boolean isAvailable;
    private LocalDate lastReStock;
    private BigDecimal price;
    private BigDecimal stock;
}
