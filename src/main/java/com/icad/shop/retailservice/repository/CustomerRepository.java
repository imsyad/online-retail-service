package com.icad.shop.retailservice.repository;

import com.icad.shop.retailservice.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByIdAndIsActive(Long id, Boolean isActive);

    Optional<Customer> findByCustomerNameAndCustomerCodeAndIsActive(String customerName, String customerCode, Boolean isActive);

    @Query(value = "SELECT * FROM customers c " +
            "WHERE LOWER(c.customer_name) LIKE LOWER(CONCAT('%', :search, '%')) " +
            "OR LOWER(c.customer_code) LIKE LOWER(CONCAT('%', :search, '%')) " +
            "ORDER BY c.customer_name", nativeQuery = true)
    Page<Customer> findByCustomerFilter(@Param("search") String search, Pageable pageable);


}

