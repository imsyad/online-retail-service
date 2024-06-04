package com.icad.shop.retailservice.service.customer.impl;

import com.icad.shop.retailservice.constant.logger.IconConstant;
import com.icad.shop.retailservice.constant.logger.MessageConstant;
import com.icad.shop.retailservice.constant.logger.StatusConstant;
import com.icad.shop.retailservice.dto.customer.CustomerListRequest;
import com.icad.shop.retailservice.dto.customer.CustomerListResponse;
import com.icad.shop.retailservice.dto.customer.CustomerUpdateRequest;
import com.icad.shop.retailservice.dto.response.ResponseDto;
import com.icad.shop.retailservice.model.Customer;
import com.icad.shop.retailservice.repository.CustomerRepository;
import com.icad.shop.retailservice.service.customer.iservice.CustomerService;
import com.icad.shop.retailservice.util.customer.CustomerMapperUtil;
import com.icad.shop.retailservice.util.logger.ResponseUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    private final CustomerMapperUtil customerMapperUtil;

    @Override
    public ResponseDto<CustomerListResponse> customerDataList(CustomerListRequest request, HttpServletRequest httpServletRequest) {
        try {
            if (Objects.isNull(request)) {
                return ResponseUtil.success(
                        StatusConstant.FAILED,
                        MessageConstant.FailedResponse.RETRIEVE_DATA,
                        IconConstant.FAILED
                );
            }

            Sort sort = Sort.by(Sort.Direction.fromString(request.getSortDir()), request.getSortBy());
            Pageable pageable = Pageable.unpaged(sort);

            Page<Customer> customerPage = customerRepository.findAll(true, pageable);

            return ResponseUtil.success(
                    StatusConstant.SUCCESS,
                    MessageConstant.SuccessResponse.RETRIEVE_DATA,
                    IconConstant.SUCCESS,
                    customerMapperUtil.customerPageToCustomerListResponse(customerPage)
            );
        } catch (Exception e) {
            return ResponseUtil.success(
                    StatusConstant.FAILED,
                    MessageConstant.FailedResponse.RETRIEVE_DATA,
                    IconConstant.FAILED
            );
        }
    }

    @Override
    public ResponseDto<String> updateCustomerData(CustomerUpdateRequest request, HttpServletRequest httpServletRequest) {
        try {
            if (Objects.isNull(request)) {
                return ResponseUtil.success(
                        StatusConstant.FAILED,
                        MessageConstant.FailedResponse.UPDATE_DATA,
                        IconConstant.FAILED
                );
            }

            Customer entityToSave = customerMapperUtil.populateCustomer(request);
            customerRepository.save(entityToSave);

            return ResponseUtil.success(
                    StatusConstant.SUCCESS,
                    MessageConstant.SuccessResponse.UPDATE_DATA,
                    IconConstant.SUCCESS,
                    "Success"
            );
        } catch (Exception e) {
            return ResponseUtil.success(
                    StatusConstant.FAILED,
                    MessageConstant.FailedResponse.UPDATE_DATA,
                    IconConstant.FAILED
            );
        }
    }

    @Override
    public ResponseDto<String> deleteCustomerData(String a, HttpServletRequest httpServletRequest) {
        return null;
    }
}
