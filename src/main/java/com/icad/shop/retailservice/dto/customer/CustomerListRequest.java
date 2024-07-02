package com.icad.shop.retailservice.dto.customer;

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
public class CustomerListRequest extends Filter implements Serializable {
    @Serial
    private static final long serialVersionUID = -2857079491245817175L;

    private String sortBy;
    private String sortDir;
}
