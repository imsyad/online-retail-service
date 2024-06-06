package com.icad.shop.retailservice.controller;

import com.icad.shop.retailservice.constant.common.ActivityConstant;
import com.icad.shop.retailservice.dto.item.ItemDeleteRequest;
import com.icad.shop.retailservice.dto.item.ItemListRequest;
import com.icad.shop.retailservice.dto.item.ItemListResponse;
import com.icad.shop.retailservice.dto.item.ItemUpdateRequest;
import com.icad.shop.retailservice.dto.response.ResponseDto;
import com.icad.shop.retailservice.service.item.iservice.ItemService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/item")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @PostMapping("/list")
    public ResponseDto<ItemListResponse> listItem(@RequestBody ItemListRequest request, HttpServletRequest httpServletrequest) {
        return itemService.listItemData(request, httpServletrequest);
    }

    @PostMapping("/add")
    public ResponseDto<Object> addItem(@RequestBody ItemUpdateRequest request, HttpServletRequest httpServletrequest) {
        return itemService.updateItemData(request, ActivityConstant.ADD, httpServletrequest);
    }

    @PostMapping("/edit")
    public ResponseDto<Object> editItem(@RequestBody ItemUpdateRequest request, HttpServletRequest httpServletRequest) {
        return itemService.updateItemData(request, ActivityConstant.EDIT, httpServletRequest);
    }

    @PostMapping("/delete")
    public ResponseDto<Object> deleteItem(@RequestBody ItemDeleteRequest request, HttpServletRequest httpServletRequest) {
        return itemService.deleteItemData(request, httpServletRequest);
    }
}
