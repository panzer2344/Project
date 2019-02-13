package com.azino.project.service.impl;

import com.azino.project.model.ShoppingBasket;
import com.azino.project.service.ShoppingBasketService;
import com.azino.project.service.SignOutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SignOutServiceImpl implements SignOutService {

    @Autowired
    private ShoppingBasketService shoppingBasketService;

    @Override
    public void signOut(String userName) {
        ShoppingBasket shoppingBasket = shoppingBasketService.findByUserName(userName);
        if(shoppingBasket != null) {
            shoppingBasketService.deleteAllItemsFromShoppingBasket(shoppingBasket.getId());
        }
    }

}
