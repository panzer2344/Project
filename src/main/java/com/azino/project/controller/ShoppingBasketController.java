package com.azino.project.controller;

import com.azino.project.model.DTO.form.DeleteFormShoppingBasket;
import com.azino.project.model.DTO.form.FormItemId;
import com.azino.project.model.Item;
import com.azino.project.model.ShoppingBasket;
import com.azino.project.service.ImageService;
import com.azino.project.service.ItemService;
import com.azino.project.service.ShoppingBasketService;
import com.azino.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.ArrayList;

@RestController
@RequestMapping("shoppingBasket")
public class ShoppingBasketController {

    @Autowired
    private ShoppingBasketService shoppingBasketService;

    @Autowired
    private UserService userService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private ItemService itemService;

    @GetMapping
    @Transactional
    public ModelAndView getSBPage(ModelMap modelMap, Principal principal) {
        User user = (User) ((Authentication) principal).getPrincipal();
        ShoppingBasket shoppingBasket = shoppingBasketService
                .findByUserName(user.getUsername()
                );
        if (null == shoppingBasket) {
            shoppingBasket = new ShoppingBasket(
                    (long) 0,
                    userService
                            .fromUserDetailsUser(user),
                    new ArrayList<>(),
                    0.0
            );
            /*shoppingBasket = shoppingBasketService
                    .findByUserName(
                            user.getUsername()
                    );*/
        }

        Double amount = 0.0;
        for (Item item : shoppingBasket.getItems()) {
            amount += item.getPrice();
        }
        shoppingBasket.setAmount(amount);

        modelMap.addAttribute("shoppingBasket", shoppingBasket);
        modelMap.addAttribute("imageService", imageService);

        return new ModelAndView("forward:/menu/items/shoppingBasket");
    }

    @PostMapping
    @Transactional
    public ModelAndView add(@ModelAttribute FormItemId itemId, ModelMap modelMap, Principal principal) {
        /* there User is com. ... . UserDetails User */
        User user = (User) ((Authentication) principal).getPrincipal();
        com.azino.project.model.User userModel = userService.fromUserDetailsUser(user);
        ShoppingBasket shoppingBasket = shoppingBasketService.findByUserName(userModel.getName());
        if (null == shoppingBasket) {
            shoppingBasket = new ShoppingBasket(
                    (long) 0,
                    userService.fromUserDetailsUser(user),
                    new ArrayList<>(),
                    0.0
            );
            shoppingBasketService.save(shoppingBasket);
            shoppingBasket = shoppingBasketService.findByUserName(userModel.getName());
        }
        shoppingBasket.getItems().add(
                itemService.findById(itemId.getId()).orElse(new Item())
        );
        return new ModelAndView("redirect:/shoppingBasket");
    }


    /* TODO: need to add pause to delete ajax query. Now it sends query many times.
     * TODO: And need to repair delete service function. Mb make force method, like
     * TODO: get list, delete entity from it, and then set this list as field in basket
     * TODO: entity
     * */

    @DeleteMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public HttpStatus delete(@RequestBody DeleteFormShoppingBasket deleteFormShoppingBasket) {
        shoppingBasketService
                .deleteItemFromShoppingBasket(
                        deleteFormShoppingBasket.getItemId(),
                        deleteFormShoppingBasket.getShoppingBasketId()
                );
        return HttpStatus.OK;
    }
}
