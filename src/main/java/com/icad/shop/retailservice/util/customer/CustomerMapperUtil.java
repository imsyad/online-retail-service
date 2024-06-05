package com.icad.shop.retailservice.util.customer;

import com.icad.shop.retailservice.dto.customer.CustomerDataDto;
import com.icad.shop.retailservice.dto.customer.CustomerListResponse;
import com.icad.shop.retailservice.dto.customer.CustomerUpdateRequest;
import com.icad.shop.retailservice.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerMapperUtil {

    public CustomerListResponse customerPageToCustomerListResponse(Page<Customer> customerPage) {
        if (!customerPage.hasContent()) {
            return CustomerListResponse.builder().build();
        }

        CustomerListResponse customerListResponse = new CustomerListResponse();

        customerListResponse.setPageNumber(customerPage.getNumber());
        customerListResponse.setPageSize(customerPage.getSize());
        customerListResponse.setTotalPages(customerPage.getTotalPages());
        customerListResponse.setTotalElements(customerPage.getTotalElements());
        customerListResponse.setCustomerList(mapListCustomerToListCustomerDataDto(customerPage.get().toList()));

        return customerListResponse;
    }

    private List<CustomerDataDto> mapListCustomerToListCustomerDataDto(List<Customer> customerList) {
        List<CustomerDataDto> customerDataDtoList;
        customerDataDtoList = customerList.stream().map(
                customer ->
                        CustomerDataDto.builder()
                                .customerAddress(customer.getCustomerAddress())
                                .customerId(customer.getId())
                                .customerName(customer.getCustomerName())
                                .lastOrderDate(customer.getLastOrderDate())
                                .isActive(customer.getIsActive())
                                .build()
        ).toList();

        return customerDataDtoList;
    }

    public Customer populateCustomer(CustomerUpdateRequest customerUpdateRequest) {
        return Customer.builder()
                .customerCode(customerUpdateRequest.getCustomerCode())
                .customerName(customerUpdateRequest.getCustomerName())
                .customerPhone(customerUpdateRequest.getCustomerPhone())
                .customerAddress(customerUpdateRequest.getCustomerAddress())
                .isActive(true)
                .pic(customerUpdateRequest.getPic())
                .build();
    }

    public void updateCustomerData(Customer customer, CustomerUpdateRequest customerUpdateRequest) {
        customer.setCustomerName(customerUpdateRequest.getCustomerName());
        customer.setCustomerAddress(customerUpdateRequest.getCustomerAddress());
        customer.setCustomerPhone(customerUpdateRequest.getCustomerPhone());
        customer.setCustomerCode(customerUpdateRequest.getCustomerCode());
        customer.setPic(customer.getPic());
    }
}
