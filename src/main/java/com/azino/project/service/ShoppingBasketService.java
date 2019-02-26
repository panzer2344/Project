package com.azino.project.service;

import com.azino.project.model.Item;
import com.azino.project.model.ShoppingBasket;
import com.azino.project.model.User;

import javax.servlet.http.HttpSession;
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

    Boolean deleteFromShoppingBasketUnlogin(Item item, HttpSession session);

    Boolean deleteFromShoppingBasketUnlogin(Long itemId, HttpSession session);

    Boolean addToShoppingBasketUnlogin(Item item, HttpSession session);

    Boolean addToShoppingBasketUnlogin(Long itemId, HttpSession session);

    ShoppingBasket getShoppingBasketFromSession(HttpSession session);

    ShoppingBasket saveFromUnloginToDatabase(HttpSession session, String username);
}
