package com.icad.shop.retailservice.service.customer.iservice;

import com.icad.shop.retailservice.constant.customer.CheckCustomerEnum;
import com.icad.shop.retailservice.dto.customer.CustomerCheckRequest;
import com.icad.shop.retailservice.dto.customer.CustomerListRequest;
import com.icad.shop.retailservice.dto.customer.CustomerListResponse;
import com.icad.shop.retailservice.dto.customer.CustomerUpdateRequest;
import com.icad.shop.retailservice.dto.response.ResponseDto;
import jakarta.servlet.http.HttpServletRequest;

public interface CustomerService {

    ResponseDto<CustomerListResponse> customerDataList(CustomerListRequest request, HttpServletRequest httpServletRequest);

    ResponseDto<String> updateCustomerData(CustomerUpdateRequest request, String action, HttpServletRequest httpServletRequest);

    ResponseDto<CheckCustomerEnum> checkCustomerData(CustomerCheckRequest request, HttpServletRequest httpServletRequest);

    ResponseDto<String> deleteCustomerData(String a, HttpServletRequest httpServletRequest);
}
