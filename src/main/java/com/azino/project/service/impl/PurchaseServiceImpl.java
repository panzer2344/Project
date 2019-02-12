package com.azino.project.service.impl;

import com.azino.project.model.DTO.form.FormPurchase;
import com.azino.project.model.Item;
import com.azino.project.model.Purchase;
import com.azino.project.repository.PurchaseRepository;
import com.azino.project.service.ItemService;
import com.azino.project.service.PurchaseService;
import com.azino.project.service.ShoppingBasketService;
import com.azino.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
                shoppingBasketService
                        .findAmountBySBId(
                                formPurchase.getShoppingBasketId())
        );
        return purchase;
    }

    @Override
    public Purchase saveFromFormPurchase(FormPurchase formPurchase) {
        return null;
    }

    @Override
    public List<Purchase> findAllPurchasesByItem(Item item) {
        List<Purchase> purchases = new ArrayList<>();
        super.repository
                .findAllPurchasesByItem(item)
                .forEach(purchases::add);
        return purchases;
    }

    @Override
    public List<Purchase> findAllPurchasesByItemId(Long id) {
        List<Purchase> purchases = new ArrayList<>();
        super.repository
                .findAllPurchasesByItemId(id)
                .forEach(purchases::add);
        return purchases;
    }
}
