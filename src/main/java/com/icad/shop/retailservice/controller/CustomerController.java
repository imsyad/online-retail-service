package com.icad.shop.retailservice.controller;

import com.icad.shop.retailservice.dto.customer.CustomerListRequest;
import com.icad.shop.retailservice.dto.customer.CustomerListResponse;
import com.icad.shop.retailservice.dto.customer.CustomerUpdateRequest;
import com.icad.shop.retailservice.dto.response.ResponseDto;
import com.icad.shop.retailservice.service.customer.iservice.CustomerService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/customer")
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping("/list")
    public ResponseDto<CustomerListResponse> customerDataList(@RequestBody CustomerListRequest request, HttpServletRequest httpServletRequest) {
        return customerService.customerDataList(request, httpServletRequest);
    }

    @PostMapping("/update")
    public ResponseDto<String> updateCustomerData(@RequestBody CustomerUpdateRequest request, HttpServletRequest httpServletRequest) {
        return customerService.updateCustomerData(request, httpServletRequest);
    }
}
