package com.icad.shop.retailservice.dto.response;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto<T> extends StatusResponseDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 8436337575685476497L;
    private T result;
}
