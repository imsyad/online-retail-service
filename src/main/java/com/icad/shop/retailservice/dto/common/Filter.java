package com.icad.shop.retailservice.dto.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Filter implements Serializable {
    @Serial
    private static final long serialVersionUID = -2605003881431656271L;

    @Builder.Default
    private Integer pageNumber = 0;
    @Builder.Default
    private Integer pageSize = 10;
    private String search;
}
