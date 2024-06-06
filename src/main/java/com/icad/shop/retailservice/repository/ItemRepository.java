package com.icad.shop.retailservice.repository;

import com.icad.shop.retailservice.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    Optional<Item> findByItemsNameAndItemsCode(String itemsName, String itemsCode);
}
