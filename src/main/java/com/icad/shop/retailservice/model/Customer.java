package com.icad.shop.retailservice.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id", nullable = false)
    private Long id;

    @Column(name = "customer_address")
    private String customerAddress;

    @Column(name = "customer_code")
    private String customerCode;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "customer_phone")
    private String customerPhone;

    @ColumnDefault("b'1'")
    @Column(name = "is_active", nullable = false)
    private Boolean isActive = false;

    @Column(name = "last_order_date")
    private LocalDate lastOrderDate;

    @Column(name = "pic")
    private String pic;

    @OneToMany(mappedBy = "customersCode")
    private Set<Order> orders = new LinkedHashSet<>();

}