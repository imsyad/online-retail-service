package com.icad.shop.retailservice.service.order.impl;

import com.icad.shop.retailservice.constant.logger.IconConstant;
import com.icad.shop.retailservice.constant.logger.MessageConstant;
import com.icad.shop.retailservice.constant.logger.StatusConstant;
import com.icad.shop.retailservice.constant.order.OrderConstant;
import com.icad.shop.retailservice.dto.order.OrderCreateRequest;
import com.icad.shop.retailservice.dto.order.OrderListRequest;
import com.icad.shop.retailservice.dto.pojo.OrderDataPojo;
import com.icad.shop.retailservice.dto.response.ResponseDto;
import com.icad.shop.retailservice.model.Customer;
import com.icad.shop.retailservice.model.Item;
import com.icad.shop.retailservice.model.Order;
import com.icad.shop.retailservice.repository.CustomerRepository;
import com.icad.shop.retailservice.repository.ItemRepository;
import com.icad.shop.retailservice.repository.OrderRepository;
import com.icad.shop.retailservice.service.order.iservice.OrderService;
import com.icad.shop.retailservice.util.logger.LoggerUtil;
import com.icad.shop.retailservice.util.logger.ResponseUtil;
import com.icad.shop.retailservice.util.order.OrderMapperUtil;
import com.icad.shop.retailservice.util.order.OrderUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {
    private final OrderUtil orderUtil;
    private final LoggerUtil loggerUtil;
    private final OrderMapperUtil orderMapperUtil;

    private final ItemRepository itemRepository;
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

    @Override
    public ResponseDto<Object> listOrder(OrderListRequest request, HttpServletRequest httpServletRequest) {
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
            Page<OrderDataPojo> orders = orderRepository.findOrderDetails(pageable);

            return ResponseUtil.success(
                    StatusConstant.SUCCESS,
                    MessageConstant.SuccessResponse.RETRIEVE_DATA,
                    IconConstant.SUCCESS,
                    orderMapperUtil.mapToOrderListResponse(orders)
            );
        } catch(Exception e) {
            loggerUtil.getStackTrace(e);
            return ResponseUtil.success();
        }
    }

    @Override
    @Transactional
    public ResponseDto<Object> createOrder(OrderCreateRequest request, HttpServletRequest httpServletRequest) {
        try {
            if (Objects.isNull(request)) {
                return ResponseUtil.success(
                        StatusConstant.SUCCESS,
                        MessageConstant.FailedResponse.CREATE_ORDER,
                        IconConstant.FAILED,
                        OrderConstant.ResponseMessage.FAILED_CREATE_ORDER
                );
            }

            Optional<Item> optionalItem = itemRepository.findByIdAndIsAvailable(request.getItemCode(), true);
            if (optionalItem.isEmpty()) {
                return ResponseUtil.success(
                        StatusConstant.SUCCESS,
                        MessageConstant.FailedResponse.CREATE_ORDER,
                        IconConstant.FAILED,
                        OrderConstant.ResponseMessage.FAILED_CREATE_ORDER
                );
            }

            Optional<Customer> optionalCustomer = customerRepository.findByIdAndIsActive(request.getCustomerCode(), true);
            if (optionalCustomer.isEmpty()) {
                return ResponseUtil.success(
                        StatusConstant.SUCCESS,
                        MessageConstant.FailedResponse.CREATE_ORDER,
                        IconConstant.FAILED,
                        OrderConstant.ResponseMessage.FAILED_CREATE_ORDER
                );
            }

            Customer customer = optionalCustomer.get();
            Item item = optionalItem.get();
            if (!isStockAvailable(item, request)) {
                return ResponseUtil.success(
                        StatusConstant.SUCCESS,
                        MessageConstant.FailedResponse.CREATE_ORDER,
                        IconConstant.FAILED,
                        OrderConstant.ResponseMessage.FAILED_CREATE_ORDER
                );
            }

            Order order = Order.builder()
                    .orderCode(orderUtil.generateOrderCode())
                    .orderDate(LocalDate.now(ZoneId.of("Asia/Jakarta")))
                    .itemsCode(item)
                    .customersCode(customer)
                    .quantity(request.getQuantity())
                    .totalPrice(item.getPrice().multiply(request.getQuantity()))
                    .build();

            orderRepository.save(order);
            updateCustomerAfterOrder(customer);
            updateItemAfterOrder(item, request);
            return ResponseUtil.success(
                    StatusConstant.SUCCESS,
                    MessageConstant.SuccessResponse.CREATE_ORDER,
                    IconConstant.SUCCESS,
                    OrderConstant.ResponseMessage.SUCCESS_CREATE_ORDER
            );
        } catch (Exception e) {
            log.error("{}", loggerUtil.getStackTrace(e));
            return ResponseUtil.success(
                    StatusConstant.FAILED,
                    MessageConstant.FailedResponse.UNEXPECTED_ERROR,
                    IconConstant.FAILED,
                    OrderConstant.ResponseMessage.FAILED_CREATE_ORDER
            );
        }
    }

    private boolean isStockAvailable(Item item, OrderCreateRequest request) {
        return item.getStock().compareTo(request.getQuantity()) > 0;
    }

    private void updateCustomerAfterOrder(Customer customer) {
        customer.setLastOrderDate(LocalDate.now(ZoneId.of("Asia/Jakarta")));
        customerRepository.save(customer);
    }

    private void updateItemAfterOrder(Item item, OrderCreateRequest request) {
        item.setStock(item.getStock().subtract(request.getQuantity()));
        if (item.getStock().compareTo(request.getQuantity()) <= 0) {
            item.setIsAvailable(false);
        }
        itemRepository.save(item);
    }
}
