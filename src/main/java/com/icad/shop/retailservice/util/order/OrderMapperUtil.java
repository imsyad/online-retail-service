package com.icad.shop.retailservice.util.order;

import com.icad.shop.retailservice.dto.order.OrderDataDto;
import com.icad.shop.retailservice.dto.order.OrderListResponse;
import com.icad.shop.retailservice.dto.pojo.OrderDataPojo;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class OrderMapperUtil {

    public OrderListResponse mapToOrderListResponse(Page<OrderDataPojo> orders) {
        return OrderListResponse.builder()
                .pageNumber(orders.getNumber())
                .pageSize(orders.getSize())
                .totalElements(orders.getTotalElements())
                .totalPages(orders.getTotalPages())
                .orders(orders.getContent().stream().map(this::mapToOrderDataDto).toList())
                .build();
    }

    private OrderDataDto mapToOrderDataDto(OrderDataPojo pojo) {
        return OrderDataDto.builder()
                .orderId(pojo.getOrderId())
                .orderCode(pojo.getOrderCode())
                .orderDate(pojo.getOrderDate())
                .quantity(pojo.getQuantity())
                .totalPrice(pojo.getTotalPrice())
                .itemName(pojo.getItemName())
                .itemCode(pojo.getItemCode())
                .customerName(pojo.getCustomerName())
                .customerCode(pojo.getCustomerCode())
                .build();
    }
}
