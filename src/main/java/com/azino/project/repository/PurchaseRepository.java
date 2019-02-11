package com.azino.project.repository;

import com.azino.project.model.Purchase;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface PurchaseRepository extends CrudRepository<Purchase, Long> {
}
