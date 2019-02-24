package com.azino.project.service;

import com.azino.project.model.Item;
import com.azino.project.model.ShoppingBasket;
import com.azino.project.model.User;

import java.math.BigDecimal;
import java.util.List;

public interface ShoppingBasketService extends BaseService<ShoppingBasket> {

    ShoppingBasket findByUserName(String username);

    User findUserBySBId(Long id);

    User findUserBySB(ShoppingBasket sb);

    BigDecimal findAmountBySBId(Long id);

    BigDecimal findAmountBySB(ShoppingBasket sb);

    List<Item> findItemsInShoppingBasketById(Long id);

    List<Item> findItemsInShoppingBasket(ShoppingBasket sb);

    void deleteItemFromShoppingBasket(Long itemId, Long shoppingBasketId);

    void deleteAllItemsFromShoppingBasket(Long shoppingBasketId);

    void addToShoppingBasket(User user, Long itemId);

    void addToShoppingBasket(User user, Item item);
}
