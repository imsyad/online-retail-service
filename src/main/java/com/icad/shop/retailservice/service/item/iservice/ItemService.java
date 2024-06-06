package com.icad.shop.retailservice.service.item.iservice;

import com.icad.shop.retailservice.dto.item.ItemDeleteRequest;
import com.icad.shop.retailservice.dto.item.ItemListRequest;
import com.icad.shop.retailservice.dto.item.ItemListResponse;
import com.icad.shop.retailservice.dto.item.ItemUpdateRequest;
import com.icad.shop.retailservice.dto.response.ResponseDto;
import jakarta.servlet.http.HttpServletRequest;

public interface ItemService {
    ResponseDto<ItemListResponse> listItemData(ItemListRequest request, HttpServletRequest httpServletRequest);
    ResponseDto<Object> updateItemData(ItemUpdateRequest request, String action, HttpServletRequest httpServletRequest);
    ResponseDto<Object> deleteItemData(ItemDeleteRequest request, HttpServletRequest httpServletRequest);

}
