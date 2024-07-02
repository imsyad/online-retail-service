package com.icad.shop.retailservice.dto.pojo;

import java.math.BigDecimal;
import java.sql.Date;

public interface OrderDataPojo1 {
    Long getOrderId();
    String getOrderCode();
    Date getOrderDate();
    BigDecimal getQuantity();
    BigDecimal getTotalPrice();
    String getItemName();
    String getItemCode();
    String getCustomerName();
    String getCustomerCode();
}