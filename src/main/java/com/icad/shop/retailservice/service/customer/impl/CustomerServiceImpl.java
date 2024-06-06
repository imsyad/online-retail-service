package com.icad.shop.retailservice.service.customer.impl;

import com.icad.shop.retailservice.constant.common.ActivityConstant;
import com.icad.shop.retailservice.constant.customer.CheckCustomerEnum;
import com.icad.shop.retailservice.constant.customer.CustomerConstant;
import com.icad.shop.retailservice.constant.logger.IconConstant;
import com.icad.shop.retailservice.constant.logger.MessageConstant;
import com.icad.shop.retailservice.constant.logger.StatusConstant;
import com.icad.shop.retailservice.dto.customer.*;
import com.icad.shop.retailservice.dto.response.ResponseDto;
import com.icad.shop.retailservice.model.Customer;
import com.icad.shop.retailservice.repository.CustomerRepository;
import com.icad.shop.retailservice.service.customer.iservice.CustomerService;
import com.icad.shop.retailservice.util.customer.CustomerMapperUtil;
import com.icad.shop.retailservice.util.logger.LoggerUtil;
import com.icad.shop.retailservice.util.logger.ResponseUtil;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    private final CustomerMapperUtil customerMapperUtil;
    private final LoggerUtil loggerUtil;

    @Override
    public ResponseDto<CustomerListResponse> customerDataList(CustomerListRequest request, HttpServletRequest httpServletRequest) {
        try {
            if (Objects.isNull(request)) {
                return ResponseUtil.success(
                        StatusConstant.SUCCESS,
                        MessageConstant.FailedResponse.RETRIEVE_DATA,
                        IconConstant.FAILED
                );
            }

            Sort sort = Sort.by(Sort.Direction.fromString(request.getSortDir()), request.getSortBy());
            Pageable pageable = PageRequest.of(request.getPageNumber(), request.getPageSize(), sort);

            Page<Customer> customerPage = customerRepository.findAllByIsActive(true, pageable);

            return ResponseUtil.success(
                    StatusConstant.SUCCESS,
                    MessageConstant.SuccessResponse.RETRIEVE_DATA,
                    IconConstant.SUCCESS,
                    customerMapperUtil.customerPageToCustomerListResponse(customerPage)
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
    public ResponseDto<String> updateCustomerData(CustomerUpdateRequest request, String action, HttpServletRequest httpServletRequest) {
        try {
            if (Objects.isNull(request)) {
                return ResponseUtil.success(
                        StatusConstant.SUCCESS,
                        MessageConstant.FailedResponse.UPDATE_DATA,
                        IconConstant.FAILED,
                        ActivityConstant.ADD.equalsIgnoreCase(action) ? CustomerConstant.ResponseMessage.FAILED_ADD_DATA : CustomerConstant.ResponseMessage.FAILED_EDIT_DATA
                );
            }

            Customer customer = null;
            if (ActivityConstant.ADD.equalsIgnoreCase(action)) {
                Optional<Customer> optionalCustomer = customerRepository.findByCustomerNameAndCustomerCodeAndIsActive(
                        request.getCustomerName(),
                        request.getCustomerCode(),
                        true);
                if (optionalCustomer.isPresent()) {
                    return ResponseUtil.success(
                            StatusConstant.SUCCESS,
                            MessageConstant.FailedResponse.UPDATE_DATA,
                            IconConstant.FAILED,
                            CustomerConstant.ResponseMessage.FAILED_ADD_DATA_ALREADY_EXIST
                    );
                }
                customer = customerMapperUtil.populateCustomer(request);
            } else if (ActivityConstant.EDIT.equalsIgnoreCase(action)) {
                Optional<Customer> optionalCustomer = customerRepository.findById(request.getCustomerId());
                if (optionalCustomer.isEmpty()) {
                    return ResponseUtil.success(
                            StatusConstant.SUCCESS,
                            MessageConstant.FailedResponse.UPDATE_DATA,
                            IconConstant.FAILED,
                            CustomerConstant.ResponseMessage.FAILED_ADD_DATA_ALREADY_EXIST
                    );
                }
                customer = optionalCustomer.get();
                customerMapperUtil.updateCustomerData(customer, request);
            }

            if (Objects.isNull(customer)) {
                return ResponseUtil.success(
                        StatusConstant.SUCCESS,
                        MessageConstant.FailedResponse.UPDATE_DATA,
                        IconConstant.FAILED,
                        CustomerConstant.ResponseMessage.FAILED_UPDATE_DATA_CUSTOMER_CANNOT_BE_NULL
                );
            }
            customerRepository.save(customer);

            return ResponseUtil.success(
                    StatusConstant.SUCCESS,
                    MessageConstant.SuccessResponse.UPDATE_DATA,
                    IconConstant.SUCCESS,
                    ActivityConstant.ADD.equalsIgnoreCase(action) ? CustomerConstant.ResponseMessage.SUCCESS_ADD_DATA : CustomerConstant.ResponseMessage.SUCCESS_EDIT_DATA
            );
        } catch (Exception e) {
            return ResponseUtil.success(
                    StatusConstant.FAILED,
                    MessageConstant.FailedResponse.UNEXPECTED_ERROR,
                    IconConstant.FAILED,
                    MessageConstant.FailedResponse.UNEXPECTED_ERROR
            );
        }
    }

    @Override
    public ResponseDto<CheckCustomerEnum> checkCustomerData(CustomerCheckRequest request, HttpServletRequest httpServletRequest) {
        final boolean IS_ACTIVE = true;
        try {
            if (Objects.isNull(request)) {
                return ResponseUtil.success(
                        StatusConstant.FAILED,
                        MessageConstant.FailedResponse.CHECK_CUSTOMER_DATA,
                        CheckCustomerEnum.NOT_AVAILABLE
                );
            }

            Optional<Customer> optionalCustomer = customerRepository.findByCustomerNameAndCustomerCodeAndIsActive(
                    request.getCustomerName(), request.getCustomerCode(), IS_ACTIVE);

            if (optionalCustomer.isPresent()) {
                return ResponseUtil.success(
                        StatusConstant.SUCCESS,
                        MessageConstant.FailedResponse.CHECK_CUSTOMER_DATA,
                        CheckCustomerEnum.NOT_AVAILABLE
                );
            }

            return ResponseUtil.success(
                    StatusConstant.SUCCESS,
                    MessageConstant.FailedResponse.CHECK_CUSTOMER_DATA,
                    CheckCustomerEnum.NOT_AVAILABLE
            );
        } catch (Exception e) {
            return ResponseUtil.success(
                    StatusConstant.FAILED,
                    MessageConstant.FailedResponse.CHECK_CUSTOMER_DATA,
                    IconConstant.FAILED
            );
        }
    }

    @Override
    public ResponseDto<String> deleteCustomerData(CustomerDeleteRequest request, HttpServletRequest httpServletRequest) {
        try {
            if(Objects.isNull(request)) {
                return ResponseUtil.success();
            }

            Optional<Customer> optionalCustomer =  customerRepository.findByIdAndIsActive(request.getCustomerId(), true);
            if (optionalCustomer.isEmpty()) {
                return ResponseUtil.success(
                        StatusConstant.FAILED,
                        MessageConstant.FailedResponse.DELETE_CUSTOMER_DATA,
                        IconConstant.FAILED,
                        CustomerConstant.ResponseMessage.FAILED_DELETE_DATA_DOES_NOT_EXIST
                );
            }

            Customer customer = optionalCustomer.get();
            customer.setIsActive(false);
            customerRepository.save(customer);

            return ResponseUtil.success();
        } catch (Exception e) {
            return ResponseUtil.success(
                    StatusConstant.FAILED,
                    MessageConstant.FailedResponse.UNEXPECTED_ERROR,
                    IconConstant.FAILED,
                    MessageConstant.FailedResponse.UNEXPECTED_ERROR
            );
        }
    }
}
