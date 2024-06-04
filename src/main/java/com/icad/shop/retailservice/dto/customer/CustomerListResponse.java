package com.icad.shop.retailservice.dto.customer;

import com.icad.shop.retailservice.dto.common.PageDto;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerListResponse extends PageDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1036040570504411112L;

    private List<CustomerDataDto> customerList;

}
