package com.icad.shop.retailservice.repository;

import com.icad.shop.retailservice.model.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    Optional<Item> findByIdAndIsAvailable(Long itemId, Boolean isAvailable);
    Optional<Item> findByItemsNameAndItemsCode(String itemsName, String itemsCode);
    @Query(value = "SELECT * FROM items i " +
            "WHERE LOWER(i.items_code) LIKE LOWER(CONCAT('%', :search, '%')) " +
            "OR LOWER(i.items_name) LIKE LOWER(CONCAT('%', :search, '%')) " +
            "ORDER BY i.items_name", nativeQuery = true)
    Page<Item> findByItemFilter(@Param("search") String search, Pageable pageable);
}
