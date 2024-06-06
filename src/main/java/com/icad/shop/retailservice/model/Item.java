package com.icad.shop.retailservice.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "items_id", nullable = false)
    private Long id;

    @ColumnDefault("b'1'")
    @Column(name = "is_available", nullable = false)
    private Boolean isAvailable = false;

    @Column(name = "items_code")
    private String itemsCode;

    @Column(name = "items_name")
    private String itemsName;

    @Column(name = "last_re_stock")
    private LocalDate lastReStock;

    @Column(name = "price", nullable = false, precision = 19, scale = 2)
    private BigDecimal price;

    @Column(name = "stock", nullable = false, precision = 19, scale = 2)
    private BigDecimal stock;

    @OneToMany(mappedBy = "itemsCode")
    private Set<Order> orders = new LinkedHashSet<>();

}