package com.icad.shop.retailservice.service.item.impl;

import com.icad.shop.retailservice.constant.common.ActivityConstant;
import com.icad.shop.retailservice.constant.item.ItemConstant;
import com.icad.shop.retailservice.constant.logger.IconConstant;
import com.icad.shop.retailservice.constant.logger.MessageConstant;
import com.icad.shop.retailservice.constant.logger.StatusConstant;
import com.icad.shop.retailservice.dto.item.ItemDeleteRequest;
import com.icad.shop.retailservice.dto.item.ItemListRequest;
import com.icad.shop.retailservice.dto.item.ItemListResponse;
import com.icad.shop.retailservice.dto.item.ItemUpdateRequest;
import com.icad.shop.retailservice.dto.response.ResponseDto;
import com.icad.shop.retailservice.model.Item;
import com.icad.shop.retailservice.repository.ItemRepository;
import com.icad.shop.retailservice.service.item.iservice.ItemService;
import com.icad.shop.retailservice.util.item.ItemMapperUtil;
import com.icad.shop.retailservice.util.logger.LoggerUtil;
import com.icad.shop.retailservice.util.logger.ResponseUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final ItemMapperUtil itemMapperUtil;
    private final LoggerUtil loggerUtil;

    @Override
    public ResponseDto<ItemListResponse> listItemData(ItemListRequest request, HttpServletRequest httpServletRequest) {
        try {
            if (Objects.isNull(request)) {
                return ResponseUtil.success(
                        StatusConstant.SUCCESS,
                        MessageConstant.FailedResponse.RETRIEVE_DATA,
                        IconConstant.FAILED);
            }

            Sort sort = Sort.by(Sort.Direction.fromString(request.getSortDir()), request.getSortBy());
            Pageable pageable = PageRequest.of(request.getPageNumber(), request.getPageSize(), sort);
            Page<Item> items = itemRepository.findAll(pageable);

            return ResponseUtil.success(
                    StatusConstant.SUCCESS,
                    MessageConstant.SuccessResponse.RETRIEVE_DATA,
                    IconConstant.SUCCESS,
                    itemMapperUtil.mapItemListResponse(items));
        } catch (Exception e) {
            log.error("{}", loggerUtil.getStackTrace(e));
            return ResponseUtil.success(
                    StatusConstant.FAILED,
                    MessageConstant.FailedResponse.UNEXPECTED_ERROR,
                    IconConstant.FAILED
            );
        }
    }

    @Override
    public ResponseDto<Object> updateItemData(ItemUpdateRequest request, String action, HttpServletRequest httpServletRequest) {
        try {
            if (Objects.isNull(request)) {
                return ResponseUtil.success(
                        StatusConstant.SUCCESS,
                        MessageConstant.FailedResponse.UPDATE_DATA,
                        IconConstant.FAILED
                );
            }

            Item item;
            if (ActivityConstant.ADD.equalsIgnoreCase(action)) {
                Optional<Item> optionalItem = itemRepository.findByItemsNameAndItemsCode(request.getItemsName(), request.getItemsCode());
                if (optionalItem.isPresent()) {
                    return ResponseUtil.success(
                            StatusConstant.SUCCESS,
                            MessageConstant.FailedResponse.UPDATE_DATA,
                            IconConstant.FAILED,
                            ItemConstant.ResponseMessage.FAILED_ADD_DATA_ALREADY_EXIST
                    );
                }

                item = Item.builder()
                        .itemsName(request.getItemsName())
                        .itemsCode(request.getItemsCode())
                        .price(request.getPrice())
                        .stock(request.getStock())
                        .isAvailable(Integer.valueOf(-1).equals(BigDecimal.ZERO.compareTo(request.getStock())))
                        .lastReStock(LocalDate.now(ZoneId.of("Asia/Jakarta")))
                        .build();
            } else {
                Optional<Item> optionalItem = itemRepository.findById(request.getItemsId());
                if (optionalItem.isEmpty()) {
                    return ResponseUtil.success(
                            StatusConstant.SUCCESS,
                            MessageConstant.FailedResponse.UPDATE_DATA,
                            IconConstant.FAILED,
                            ItemConstant.ResponseMessage.FAILED_EDIT_DATA_DOES_NOT_EXIST
                    );
                }

                Optional<Item> duplicatedItem = itemRepository.findByItemsNameAndItemsCode(request.getItemsName(), request.getItemsCode());
                if (duplicatedItem.isPresent() && !optionalItem.get().getId().equals(duplicatedItem.get().getId())) {
                    return ResponseUtil.success(
                            StatusConstant.SUCCESS,
                            MessageConstant.FailedResponse.UPDATE_DATA,
                            IconConstant.FAILED,
                            ItemConstant.ResponseMessage.FAILED_EDIT_DATA_NAME_OR_CODE_ALREADY_USED
                    );
                }

                item = optionalItem.get();
                item.setItemsName(request.getItemsName());
                item.setItemsCode(request.getItemsCode());
                item.setPrice(request.getPrice());
                item.setIsAvailable(Integer.valueOf(-1).equals(BigDecimal.ZERO.compareTo(request.getStock())));
                // If the edit request and the current item stock is different, update the last restock value with current date
                if (Integer.valueOf(1).equals(request.getStock().compareTo(item.getStock()))) {
                    item.setLastReStock(LocalDate.now(ZoneId.of("Asia/Jakarta")));
                }
                item.setStock(request.getStock());
            }

            itemRepository.save(item);

            return ResponseUtil.success(
                    StatusConstant.SUCCESS,
                    MessageConstant.SuccessResponse.UPDATE_DATA,
                    IconConstant.SUCCESS,
                    ActivityConstant.ADD.equalsIgnoreCase(action) ? ItemConstant.ResponseMessage.SUCCESS_ADD_DATA : ItemConstant.ResponseMessage.SUCCESS_EDIT_DATA
            );
        } catch (Exception e) {
            log.error("{}", loggerUtil.getStackTrace(e));
            return ResponseUtil.success(
                    StatusConstant.FAILED,
                    MessageConstant.FailedResponse.UNEXPECTED_ERROR,
                    IconConstant.FAILED
            );
        }
    }

    @Override
    public ResponseDto<Object> deleteItemData(ItemDeleteRequest request, HttpServletRequest httpServletRequest) {
        try {
            if (Objects.isNull(request)) {
                return ResponseUtil.success(
                        StatusConstant.SUCCESS,
                        MessageConstant.FailedResponse.DELETE_DATA,
                        IconConstant.FAILED
                );
            }

            Optional<Item> optionalItem = itemRepository.findById(request.getItemsId());

            if (optionalItem.isEmpty()) {
                return ResponseUtil.success(
                        StatusConstant.SUCCESS,
                        MessageConstant.FailedResponse.DELETE_DATA,
                        IconConstant.FAILED,
                        ItemConstant.ResponseMessage.FAILED_DELETE_DATA_DOES_NOT_EXIST
                );
            }

            Item item = optionalItem.get();
            itemRepository.delete(item);

            return ResponseUtil.success(
                    StatusConstant.SUCCESS,
                    MessageConstant.SuccessResponse.DELETE_DATA,
                    IconConstant.SUCCESS,
                    ItemConstant.ResponseMessage.SUCCESS_DELETE_DATA
            );

        } catch (Exception e) {
            log.error("{}", loggerUtil.getStackTrace(e));
            return ResponseUtil.success(
                    StatusConstant.FAILED,
                    MessageConstant.FailedResponse.UNEXPECTED_ERROR,
                    IconConstant.FAILED
            );
        }
    }
}
