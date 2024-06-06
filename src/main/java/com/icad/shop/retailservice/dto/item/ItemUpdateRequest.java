package com.icad.shop.retailservice.dto.item;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemUpdateRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = 3164594237982088546L;

    private long itemsId;
    private String itemsCode;
    private String itemsName;
    private BigDecimal price;
    private BigDecimal stock;
}
