package com.icad.shop.retailservice.dto.item;

import com.icad.shop.retailservice.dto.common.Filter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ItemListRequest extends Filter implements Serializable {
    @Serial
    private static final long serialVersionUID = -1988353199089538493L;
    private String startDate;
    private String endDate;
    private String sortBy;
    private String sortDir;
}
