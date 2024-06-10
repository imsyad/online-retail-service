package com.icad.shop.retailservice.repository;

import com.icad.shop.retailservice.dto.pojo.OrderDataPojo;
import com.icad.shop.retailservice.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value = "select o.order_id AS orderId, o.order_code AS orderCode, o.order_date AS orderDate, " +
            "o.quantity AS quantity, o.total_price AS totalPrice, " +
            "i.items_name AS itemName, i.items_code AS itemCode, " +
            "c.customer_name AS customerName, c.customer_code AS customerCode " +
            "from orders o inner join items i ON o.items_code = i.items_id " +
            "inner join customers c on c.customer_id = o.customers_code",
            nativeQuery = true)
    Page<OrderDataPojo> findOrderDetails(Pageable pageable);
}
