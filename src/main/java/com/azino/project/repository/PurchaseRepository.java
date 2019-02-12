package com.azino.project.repository;

import com.azino.project.model.Item;
import com.azino.project.model.Purchase;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface PurchaseRepository extends CrudRepository<Purchase, Long> {

    @Query("select p from Purchase p join p.items i where i.id = :id")
    Iterable<Purchase> findAllPurchasesByItemId(@Param("id") Long id);

    @Query("select p from Purchase p join p.items i where i = :item")
    Iterable<Purchase> findAllPurchasesByItem(@Param("item") Item item);

}
