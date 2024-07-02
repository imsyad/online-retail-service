package com.icad.shop.retailservice.controller;

import com.icad.shop.retailservice.dto.order.OrderCreateRequest;
import com.icad.shop.retailservice.dto.order.OrderListRequest;
import com.icad.shop.retailservice.dto.response.ResponseDto;
import com.icad.shop.retailservice.service.order.iservice.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/list")
    public ResponseDto<Object> listOrder(@RequestBody OrderListRequest request, HttpServletRequest httpServletRequest) {
        return orderService.listOrder(request, httpServletRequest);
    }

    @PostMapping("/create")
    public ResponseDto<Object> createOrder(@RequestBody OrderCreateRequest request, HttpServletRequest httpServletRequest) {
        return orderService.createOrder(request, httpServletRequest);
    }

    @PostMapping("/export")
    public ResponseDto<Object> exportOrder(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        return orderService.exportOrder(httpServletRequest, httpServletResponse);
    }
}
