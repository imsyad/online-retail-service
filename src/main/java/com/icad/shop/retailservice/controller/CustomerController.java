package com.icad.shop.retailservice.controller;

import com.icad.shop.retailservice.constant.common.ActivityConstant;
import com.icad.shop.retailservice.constant.customer.CheckCustomerEnum;
import com.icad.shop.retailservice.dto.customer.*;
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

    @PostMapping("/add")
    public ResponseDto<String> addCustomerData(@RequestBody CustomerUpdateRequest request, HttpServletRequest httpServletRequest) {
        return customerService.updateCustomerData(request, ActivityConstant.ADD, httpServletRequest);
    }

    @PostMapping("/edit")
    public ResponseDto<String> editCustomerData(@RequestBody CustomerUpdateRequest request, HttpServletRequest httpServletRequest) {
        return customerService.updateCustomerData(request, ActivityConstant.EDIT, httpServletRequest);
    }

    @PostMapping("/delete")
    public ResponseDto<String> deleteCustomerData(@RequestBody CustomerDeleteRequest request, HttpServletRequest httpServletRequest) {
        return customerService.deleteCustomerData(request, httpServletRequest);
    }

    @PostMapping("/check-customer")
    public ResponseDto<CheckCustomerEnum> checkCustomerData(@RequestBody CustomerCheckRequest request, HttpServletRequest httpServletRequest) {
        return customerService.checkCustomerData(request, httpServletRequest);
    }
}
