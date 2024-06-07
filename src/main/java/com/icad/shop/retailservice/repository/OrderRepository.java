package com.icad.shop.retailservice.repository;

import com.icad.shop.retailservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value = "SELECT MAX(o.order_id) FROM Order o", nativeQuery = true)
    Long getLastRecordId();
}
