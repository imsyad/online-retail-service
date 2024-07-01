package com.icad.shop.retailservice.repository;

import com.icad.shop.retailservice.dto.pojo.OrderDataPojo;
import com.icad.shop.retailservice.dto.pojo.OrderDataPojo1;
import com.icad.shop.retailservice.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value = "select o.order_id AS orderId, o.order_code AS orderCode, o.order_date AS orderDate, " +
            "o.quantity AS quantity, o.total_price AS totalPrice, " +
            "i.items_name AS itemName, i.items_code AS itemCode, " +
            "c.customer_name AS customerName, c.customer_code AS customerCode " +
            "from orders o inner join items i ON o.items_code = i.items_id " +
            "inner join customers c on c.customer_id = o.customers_code " +
            "where " +
            "lower(i.items_name) like lower(concat('%', :search, '%')) " +
            "or lower(c.customer_name) like lower(concat('%', :search, '%')) " +
            "or lower(c.customer_code) like lower(concat('%', :search, '%')) ",
            nativeQuery = true)
    Page<OrderDataPojo> findOrderDetails(@Param("search") String search, Pageable pageable);

    @Query(value="select o.order_id AS orderId, o.order_code AS orderCode, o.order_date AS orderDate, o.quantity AS quantity, " +
            "o.total_price AS totalPrice, i.items_name AS itemName, i.items_code AS itemCode, c.customer_name AS customerName, " +
            "c.customer_code AS customerCode " +
            "from orders o " +
            "left join customers c on c.customer_id = o.customers_code " +
            "left join items i on i.items_id = o.items_code " +
            "order by order_date desc",
            nativeQuery = true)
    List<OrderDataPojo1> findAllOrderDetails();
}
