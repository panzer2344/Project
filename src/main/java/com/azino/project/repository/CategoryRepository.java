package com.azino.project.repository;

import com.azino.project.model.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface CategoryRepository extends CrudRepository<Category, Long> {
    @Override
    @Query("select c from Category c order by c.id asc")
    Iterable<Category> findAll();
}
