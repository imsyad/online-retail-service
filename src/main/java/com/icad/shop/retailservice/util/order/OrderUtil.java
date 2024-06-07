package com.icad.shop.retailservice.util.order;

import com.icad.shop.retailservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class OrderUtil {
    private final OrderRepository orderRepository;

    public String generateOrderCode() {
        return "T-" + LocalDate.now() + orderRepository.getLastRecordId();
    }
}
