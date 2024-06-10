package com.icad.shop.retailservice.dto.order;

import com.icad.shop.retailservice.dto.common.Filter;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class OrderListRequest extends Filter implements Serializable {
    @Serial
    private static final long serialVersionUID = -1026542974861952586L;

    private long customerId;
    private long itemId;
    private long orderId;
    private String sortBy;
    private String sortDir;
}
