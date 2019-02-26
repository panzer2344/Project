package com.azino.project.controller;

import com.azino.project.model.Item;
import com.azino.project.model.ShoppingBasket;
import com.azino.project.model.User;
import com.azino.project.service.CategoryService;
import com.azino.project.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("menu")
//@SessionAttributes("shoppingBasket")
public class MenuController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private MenuService menuService;

    @GetMapping({"{viewDir}/{viewName}", "{viewName}"})
    public ModelAndView pageWithMenu(ModelMap model, @PathVariable String viewName, @PathVariable Optional<String> viewDir/*,
                                     @ModelAttribute("shoppingBasket") ShoppingBasket shoppingBasket, Principal principal*/) {
        String resultViewName = viewName;
        if (viewDir.isPresent()) {
            resultViewName = viewDir.get() + '/' + viewName;
        }
        ModelAndView modelAndView = new ModelAndView(resultViewName, model);
        menuService.addCategoriesToMenu(modelAndView);
        //to create session attribute for guest basket
        //addSessionAttributeIfRequired(principal, model, shoppingBasket);
        return modelAndView;
    }

    /*public void addSessionAttributeIfRequired(Principal principal,  ModelMap modelMap,
                                     @ModelAttribute("shoppingBasket") ShoppingBasket shoppingBasket){
        if(principal == null){
            if(shoppingBasket == null) {
                shoppingBasket = new ShoppingBasket((long) 0, new User(), new ArrayList<Item>(), BigDecimal.ZERO);
                modelMap.addAttribute("shoppingBasket", shoppingBasket);
            }
        }
    }*/

}
