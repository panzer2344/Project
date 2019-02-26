package com.azino.project.service.impl;

import com.azino.project.model.ShoppingBasket;
import com.azino.project.service.ShoppingBasketService;
import com.azino.project.service.SignOutService;
import com.azino.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;

@Service
@Transactional
public class SignOutServiceImpl implements SignOutService {

    @Autowired
    private ShoppingBasketService shoppingBasketService;

    @Autowired
    private UserService userService;

    @Override
    public void signOut(String userName, HttpSession session) {
        ShoppingBasket shoppingBasket = shoppingBasketService.findByUserName(userName);
        if(shoppingBasket != null) {
            userService.deleteSessionFromActiveSessions(userName, session.getId());
            if(userService.getAllActiveSessions(userName).isEmpty()) {
                shoppingBasketService.deleteAllItemsFromShoppingBasket(shoppingBasket.getId());
                shoppingBasketService.delete(shoppingBasket);
            }
            session.invalidate();
        }
    }

}
