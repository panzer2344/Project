package com.azino.project.service.impl;

import com.azino.project.model.DTO.form.FormPurchase;
import com.azino.project.model.Purchase;
import com.azino.project.repository.PurchaseRepository;
import com.azino.project.service.ItemService;
import com.azino.project.service.PurchaseService;
import com.azino.project.service.ShoppingBasketService;
import com.azino.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseServiceImpl
        extends BaseServiceImpl<Purchase, PurchaseRepository>
        implements PurchaseService {

    @Autowired
    private UserService userService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private ShoppingBasketService shoppingBasketService;

    public PurchaseServiceImpl(PurchaseRepository purchaseRepository) {
        super(purchaseRepository);
    }

    @Override
    public Purchase fromFormPurchase(FormPurchase formPurchase) {
        Purchase purchase = new Purchase(
                (long) 0,
                shoppingBasketService
                        .findUserBySBId(
                                formPurchase.getShoppingBasketId()),
                shoppingBasketService
                        .findItemsInShoppingBasketById(
                                formPurchase.getShoppingBasketId()),
                /*itemService
                        .findAllByShoppingBasketId(
                                formPurchase.getShoppingBasketId()),*/
                shoppingBasketService
                        .findAmountBySBId(
                                formPurchase.getShoppingBasketId())
        );
        /*Iterable<Item> items = itemService
                .findAllById((Iterable<Long>) formPurchase.getItemsId());
        List<Item> itemList = new ArrayList<>();
        for(Item item : items){
            itemList.add(item);
        }

        Purchase purchase = new Purchase(
                (long) 0,
                userService
                        .findByName(formPurchase.getUserName()),
                itemList,
                *//*
                itemService
                        .findAllById(formPurchase.getItemsId()),*//*
                formPurchase.getAmount()
                );*/
        return purchase;
    }

    @Override
    public Purchase saveFromFormPurchase(FormPurchase formPurchase) {
        return null;
    }
}
