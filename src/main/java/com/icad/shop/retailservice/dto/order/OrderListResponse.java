package com.icad.shop.retailservice.dto.order;

import com.icad.shop.retailservice.dto.common.PageDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class OrderListResponse extends PageDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 6518461277052086316L;

    private List<OrderDataDto> orders;
}
