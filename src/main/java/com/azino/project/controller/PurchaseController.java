package com.azino.project.controller;

import com.azino.project.model.DTO.form.FormPurchase;
import com.azino.project.model.Purchase;
import com.azino.project.service.PurchaseService;
import com.azino.project.service.ShoppingBasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("purchase")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private ShoppingBasketService shoppingBasketService;

    @PostMapping
    public ModelAndView add(@ModelAttribute FormPurchase formPurchase) {
        if (!formPurchase.getShoppingBasketId().equals((long) 0)) {
            Purchase purchase = purchaseService.fromFormPurchase(formPurchase);
            Purchase result = purchaseService.save(purchase);
            if (!result.getId().equals((long) 0)) {
                shoppingBasketService.deleteById(formPurchase.getShoppingBasketId());
                return new ModelAndView("redirect:/menu/items/successfullPurchase");
            }
        }
        return new ModelAndView("redirect:/shoppingBasket");
    }
}
