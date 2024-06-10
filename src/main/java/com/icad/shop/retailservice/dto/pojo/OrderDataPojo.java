package com.icad.shop.retailservice.dto.pojo;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface OrderDataPojo {
    Long getOrderId();
    String getOrderCode();
    LocalDate getOrderDate();
    BigDecimal getQuantity();
    BigDecimal getTotalPrice();
    String getItemName();
    String getItemCode();
    String getCustomerName();
    String getCustomerCode();
}
