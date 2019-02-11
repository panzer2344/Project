package com.azino.project.service.impl;

import com.azino.project.model.Item;
import com.azino.project.model.ShoppingBasket;
import com.azino.project.model.User;
import com.azino.project.repository.ItemRepository;
import com.azino.project.repository.ShoppingBasketRepository;
import com.azino.project.service.ShoppingBasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ShoppingBasketServiceImpl
        extends BaseServiceImpl<ShoppingBasket, ShoppingBasketRepository>
        implements ShoppingBasketService {

    @Autowired
    private ItemRepository itemRepository;

    public ShoppingBasketServiceImpl(ShoppingBasketRepository shoppingBasketRepository) {
        super(shoppingBasketRepository);
    }

    @Override
    public ShoppingBasket findByUserName(String username) {
        return super.repository.findByUser_Name(username);
    }

    @Override
    public User findUserBySBId(Long id) {
        return super.repository.findUserByShoppingBasketId(id);
    }

    @Override
    public User findUserBySB(ShoppingBasket sb) {
        return super.repository.findUserByShoppingBasketId(sb.getId());
    }

    @Override
    public Double findAmountBySBId(Long id) {
        return super.repository.findAmountByShoppingBasketId(id);
    }

    @Override
    public Double findAmountBySB(ShoppingBasket sb) {
        return super.repository.findAmountByShoppingBasketId(sb.getId());
    }

    @Override
    public List<Item> findItemsInShoppingBasketById(Long id) {
        Iterable<Item> items = super.repository.findItemsInShoppingBasketById(id);
        List<Item> itemList = new ArrayList<>();
        items.forEach(itemList::add);
        return itemList;
    }

    @Override
    public List<Item> findItemsInShoppingBasket(ShoppingBasket sb) {
        return findItemsInShoppingBasketById(sb.getId());
    }

    @Override
    @Transactional
    public void deleteItemFromShoppingBasket(Long itemId, Long shoppingBasketId) {
        //super.repository.deleteItemFromShoppingBasket(itemId, shoppingBasketId);
        Item item = itemRepository.findById(itemId).orElse(new Item());
        List<Item> items = findItemsInShoppingBasketById(shoppingBasketId);
        items.remove(item);
        super.repository
                .findById(shoppingBasketId)
                .get()
                .setItems(items);
    }
}
