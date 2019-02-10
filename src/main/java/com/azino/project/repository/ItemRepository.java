package com.azino.project.repository;

import com.azino.project.model.Category;
import com.azino.project.model.Item;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends CrudRepository<Item, Long> {

    @Query("select i from ShoppingBasket sb join sb.items i where :id = sb.id")
    Iterable<Item> findAllByShoppingBasketId(@Param("id") Long id);

    Iterable<Item> findAllByCategoriesContains(Category category);

    //@Query("select i From Item i Where :id in (select c.id from i.categories c)")
    //Iterable<Item> findAllByCategoriesContains(@Param("id") Long id);

    @Query("select i From Item i join i.categories c where :id = c.id")
    Iterable<Item> findAllByCategoriesContains(@Param("id") Long id);

    @Query("select i From Item i join i.categories c where c in :categories")
    Iterable<Item> findAllByCategoriesContains(@Param("categories") List<Category> categories);

    @Query("select i From Item i join i.categories c where c.id in :ids")
    Iterable<Item> findAllByCategoriesContainsIds(@Param("ids") List<Long> ids);

    @Query("select i From Item i join i.categories c where c in :categories")
    Iterable<Item> findAllByCategoriesContains(@Param("categories") Iterable<Category> categories);

    @Query("select i From Item i join i.categories c where c.id in :ids")
    Iterable<Item> findAllByCategoriesContainsIds(@Param("ids") Iterable<Long> ids);
}
