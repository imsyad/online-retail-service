package com.icad.shop.retailservice.service.order.impl;

import com.icad.shop.retailservice.constant.logger.IconConstant;
import com.icad.shop.retailservice.constant.logger.MessageConstant;
import com.icad.shop.retailservice.constant.logger.StatusConstant;
import com.icad.shop.retailservice.dto.order.OrderCreateRequest;
import com.icad.shop.retailservice.dto.response.ResponseDto;
import com.icad.shop.retailservice.model.Customer;
import com.icad.shop.retailservice.model.Item;
import com.icad.shop.retailservice.model.Order;
import com.icad.shop.retailservice.repository.CustomerRepository;
import com.icad.shop.retailservice.repository.ItemRepository;
import com.icad.shop.retailservice.repository.OrderRepository;
import com.icad.shop.retailservice.service.order.iservice.OrderService;
import com.icad.shop.retailservice.util.logger.ResponseUtil;
import com.icad.shop.retailservice.util.order.OrderUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderUtil orderUtil;

    private final ItemRepository itemRepository;
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

    @Override
    public ResponseDto<Object> listOrder(HttpServletRequest request) {
        return null;
    }

    @Override
    @Transactional
    public ResponseDto<Object> createOrder(OrderCreateRequest request, HttpServletRequest httpServletRequest) {
        try {
            if (Objects.isNull(request)) {
                return ResponseUtil.success(
                        StatusConstant.SUCCESS,
                        MessageConstant.FailedResponse.CREATE_ORDER,
                        IconConstant.FAILED
                );
            }

            Optional<Item> optionalItem = itemRepository.findByIdAndIsAvailable(request.getItemCode(), true);
            if (optionalItem.isEmpty()) {
                return ResponseUtil.success(
                        StatusConstant.SUCCESS,
                        MessageConstant.FailedResponse.CREATE_ORDER,
                        IconConstant.FAILED
                );
            }

            Optional<Customer> optionalCustomer = customerRepository.findByIdAndIsActive(request.getCustomerCode(), true);
            if (optionalCustomer.isEmpty()) {
                return ResponseUtil.success(
                        StatusConstant.SUCCESS,
                        MessageConstant.FailedResponse.CREATE_ORDER,
                        IconConstant.FAILED
                );
            }

            Customer customer = optionalCustomer.get();
            Item item = optionalItem.get();
            if (!isStockAvailable(item, request)) {
                return ResponseUtil.success(
                        StatusConstant.SUCCESS,
                        MessageConstant.FailedResponse.CREATE_ORDER,
                        IconConstant.FAILED
                );
            }

            Order order = Order.builder()
                    .orderCode(orderUtil.generateOrderCode())
                    .orderDate(LocalDate.now(ZoneId.of("Asia/Jakarta")))
                    .itemsCode(item)
                    .customersCode(customer)
                    .quantity(request.getQuantity())
                    .totalPrice(request.getItemPrice().multiply(request.getQuantity()))
                    .build();

            orderRepository.save(order);
            updateCustomerAfterOrder(customer, order);
            updateItemAfterOrder(item, order, request);
            return ResponseUtil.success(
                    StatusConstant.SUCCESS,
                    MessageConstant.FailedResponse.CREATE_ORDER,
                    IconConstant.SUCCESS);
        } catch (Exception e) {
            return ResponseUtil.success(
                    StatusConstant.FAILED,
                    MessageConstant.FailedResponse.UNEXPECTED_ERROR,
                    IconConstant.FAILED);
        }
    }

    private boolean isStockAvailable(Item item, OrderCreateRequest request) {
        return item.getStock().compareTo(request.getQuantity()) >= 0;
    }

    private void updateCustomerAfterOrder(Customer customer, Order order) {
        Set<Order> orderSet = customer.getOrders();
        orderSet.add(order);
        customer.setOrders(orderSet);
        customer.setLastOrderDate(LocalDate.now(ZoneId.of("Asia/Jakarta")));
        customerRepository.save(customer);
    }

    private void updateItemAfterOrder(Item item, Order order, OrderCreateRequest request) {
        item.setStock(item.getStock().subtract(request.getQuantity()));
        if (item.getStock().compareTo(request.getQuantity()) <= 0) {
            item.setIsAvailable(false);
        }
        Set<Order> orderSet = item.getOrders();
        orderSet.add(order);
        orderRepository.save(order);
    }
}
