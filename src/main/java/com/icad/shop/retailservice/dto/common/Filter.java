package com.icad.shop.retailservice.dto.common;

import lombok.AllArgsConstructor;
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
    private static final long serialVersionUID = 4026653665859063100L;

    private String search;
}
