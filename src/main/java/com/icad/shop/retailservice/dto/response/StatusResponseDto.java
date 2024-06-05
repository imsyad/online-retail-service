package com.icad.shop.retailservice.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty(index = 1)
    private String code;
    @JsonProperty(index = 2)
    private String message;
    @JsonProperty(index = 3)
    private String icon;
}
