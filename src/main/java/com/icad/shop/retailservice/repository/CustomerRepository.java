package com.icad.shop.retailservice.repository;

import com.icad.shop.retailservice.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Page<Customer> findAllByIsActive(Boolean isActive, Pageable pageable);
    Page<Customer> findAllByCustomerNameAndIsActive(String customerName, Boolean isActive, Pageable pageable);
    Page<Customer> findAllByCustomerName(String customerName, Pageable pageable);
    Optional<Customer> findByCustomerNameAndCustomerCodeAndIsActive(String customerName, String customerCode, Boolean isActive);
}
