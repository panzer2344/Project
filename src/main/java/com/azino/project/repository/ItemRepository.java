package com.azino.project.repository;

import com.azino.project.model.Category;
import com.azino.project.model.Item;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends CrudRepository<Item, Long> {

    Iterable<Item> findAllByCategoriesContains(Category category);

    //@Query("select i From Item i Where :id in (select c.id from i.categories c)")
    //Iterable<Item> findAllByCategoriesContains(@Param("id") Long id);

    @Query("select i From Item i join i.categories c where :id = c.id")
    Iterable<Item> findAllByCategoriesContains(@Param("id") Long id);
}
