package com.azino.project.repository;

import com.azino.project.model.Item;
import com.azino.project.model.ShoppingBasket;
import com.azino.project.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ShoppingBasketRepository extends CrudRepository<ShoppingBasket, Long> {

    ShoppingBasket findByUser_Name(String name);

    @Query("select sb.user from ShoppingBasket sb where sb.id = :id")
    User findUserByShoppingBasketId(@Param("id") Long id);

    @Query("select sb.amount from ShoppingBasket sb where sb.id = :id")
    Double findAmountByShoppingBasketId(@Param("id") Long id);

    @Query("select i from ShoppingBasket sb join sb.items i where sb.id = :id")
    Iterable<Item> findItemsInShoppingBasketById(@Param("id") Long id);

    //@Query("delete from ShoppingBasket sb join sb.items i where sb.id = :sbId and i.id = :iId")
    /*@Modifying
    @Query(
            nativeQuery = true,
            value = "delete from public.shopping_basket_items as sbi where sbi.shopping_basket_id = :iId and sbi.items_id = :sbId"
    )
    void deleteItemFromShoppingBasket(@Param("iId") Long itemId, @Param("sbId") Long shoppingBasketId);*/

}