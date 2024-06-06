package com.icad.shop.retailservice.service.customer.iservice;

import com.icad.shop.retailservice.constant.customer.CheckCustomerEnum;
import com.icad.shop.retailservice.dto.customer.*;
import com.icad.shop.retailservice.dto.response.ResponseDto;
import jakarta.servlet.http.HttpServletRequest;

public interface CustomerService {

    ResponseDto<CustomerListResponse> customerDataList(CustomerListRequest request, HttpServletRequest httpServletRequest);

    ResponseDto<String> updateCustomerData(CustomerUpdateRequest request, String action, HttpServletRequest httpServletRequest);

    ResponseDto<String> deleteCustomerData(CustomerDeleteRequest request, HttpServletRequest httpServletRequest);

    ResponseDto<CheckCustomerEnum> checkCustomerData(CustomerCheckRequest request, HttpServletRequest httpServletRequest);

}
