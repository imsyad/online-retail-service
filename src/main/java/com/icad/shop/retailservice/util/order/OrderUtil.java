package com.icad.shop.retailservice.util.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class OrderUtil {

    public String generateOrderCode() {
        return "T-" + LocalDate.now() + UUID.randomUUID();
    }
}
