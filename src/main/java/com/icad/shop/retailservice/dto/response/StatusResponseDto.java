package com.icad.shop.retailservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class StatusResponseDto implements Serializable {
    @Serial
    private static final long serialVersionUID = -4460358170564571220L;
    private String code;
    private String icon;
    private String message;
}
