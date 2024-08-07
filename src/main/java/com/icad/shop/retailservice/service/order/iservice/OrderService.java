package com.icad.shop.retailservice.service.order.iservice;

import com.icad.shop.retailservice.dto.order.OrderCreateRequest;
import com.icad.shop.retailservice.dto.order.OrderListRequest;
import com.icad.shop.retailservice.dto.response.ResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface OrderService {
    ResponseDto<Object> createOrder(OrderCreateRequest request, HttpServletRequest httpServletRequest);
    ResponseDto<Object> listOrder(OrderListRequest request, HttpServletRequest httpServletRequest);
    ResponseDto<Object> exportOrder(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse);
}
