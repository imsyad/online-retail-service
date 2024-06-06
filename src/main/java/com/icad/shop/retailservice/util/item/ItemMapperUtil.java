package com.icad.shop.retailservice.util.item;

import com.icad.shop.retailservice.dto.item.ItemDataDto;
import com.icad.shop.retailservice.dto.item.ItemListResponse;
import com.icad.shop.retailservice.model.Item;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ItemMapperUtil {

    public ItemListResponse mapItemListResponse(Page<Item> itemPage) {
        ItemListResponse itemListResponse = new ItemListResponse();
        itemListResponse.setPageNumber(itemPage.getNumber());
        itemListResponse.setPageSize(itemPage.getSize());
        itemListResponse.setTotalPages(itemPage.getTotalPages());
        itemListResponse.setTotalElements(itemPage.getTotalElements());

        if (!itemPage.hasContent()) {
            return itemListResponse;
        }

        List<ItemDataDto> itemList = itemPage.get().map(this::mapItemData).toList();
        itemListResponse.setItemList(itemList);

        return itemListResponse;
    }

    private ItemDataDto mapItemData(Item item) {
        return ItemDataDto.builder()
                .itemsId(item.getId())
                .itemsCode(item.getItemsCode())
                .itemName(item.getItemsName())
                .price(item.getPrice())
                .stock(item.getStock())
                .lastReStock(item.getLastReStock())
                .isAvailable(item.getIsAvailable())
                .build();
    }
}
