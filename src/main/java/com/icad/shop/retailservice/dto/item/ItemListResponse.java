package com.icad.shop.retailservice.dto.item;

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
public class ItemListResponse extends PageDto implements Serializable {
    @Serial
    private static final long serialVersionUID = -1435237257533243758L;

    private List<ItemDataDto> itemList;
}
