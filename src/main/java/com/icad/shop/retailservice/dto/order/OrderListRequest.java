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
    private static final long serialVersionUID = -701468976114917207L;

    private String sortBy;
    private String sortDir;
}
