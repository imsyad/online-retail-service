package com.icad.shop.retailservice.repository;

import com.icad.shop.retailservice.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByIdAndIsActive(Long id, Boolean isActive);
    Optional<Customer> findByCustomerNameAndCustomerCodeAndIsActive(String customerName, String customerCode, Boolean isActive);
}
