package com.azino.project.controller;

import com.azino.project.constants.Constant;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("shoppingBasket")
//@SessionAttributes({"shoppingBasket"})
@SessionAttributes(types = ShoppingBasket.class)
public class ShoppingBasketController {

    @Autowired
    private ShoppingBasketService shoppingBasketService;

    @Autowired
    private UserService userService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private ItemService itemService;



    /*@GetMapping("start")
    public ModelAndView start(ModelMap modelMap){
        modelMap.addAttribute(new ShoppingBasket((long) 0, new com.azino.project.model.User(), new ArrayList<Item>(), BigDecimal.ZERO));
        return new ModelAndView("forward:/");
    }*/

    /*@ModelAttribute("shoppingBasket")
    public ShoppingBasket createShoppingBasket(ShoppingBasket shoppingBasket){
        if(shoppingBasket == null) {
            return new ShoppingBasket((long) 0, new com.azino.project.model.User(), new ArrayList<Item>(), BigDecimal.ZERO);
        }
        return shoppingBasket;
    }*/

    @GetMapping
    @Transactional
    public ModelAndView getSBPage(ModelMap modelMap, Principal principal,
                                  /*@ModelAttribute("shoppingBasket")*/ /*ShoppingBasket shoppingBasket*/
                                  HttpServletRequest httpServletRequest) {
        ShoppingBasket shoppingBasket = null;
        if (principal != null) {
            User user = (User) ((Authentication) principal).getPrincipal();
            /*ShoppingBasket shoppingBasket = shoppingBasketService
                    .findByUserName(user.getUsername()
                    );*/
            shoppingBasket = shoppingBasketService
                    .findByUserName(user.getUsername()
                    );
            if (null == shoppingBasket) {
                shoppingBasket = new ShoppingBasket(
                        (long) 0,
                        userService
                                .fromUserDetailsUser(user),
                        new ArrayList<>(),
                        BigDecimal.ZERO
                );
            /*shoppingBasket = shoppingBasketService
                    .findByUserName(
                            user.getUsername()
                    );*/
            }
            BigDecimal amount = BigDecimal.ZERO;
            for (Item item : shoppingBasket.getItems()) {
                amount = amount.add(item.getPrice());
            }
            shoppingBasket.setAmount(amount);
        }else{
            /*if (shoppingBasket == null){
                shoppingBasket =  new ShoppingBasket((long) 0, new com.azino.project.model.User(), new ArrayList<Item>(), BigDecimal.ZERO);
            }*/
            shoppingBasket = (ShoppingBasket) httpServletRequest.getSession().getAttribute(Constant.SHOPPING_BASKET_SESSION_NAME);
            if(shoppingBasket == null){
                shoppingBasket =  new ShoppingBasket((long) 0, new com.azino.project.model.User(), new ArrayList<Item>(), BigDecimal.ZERO);
                httpServletRequest.getSession().setAttribute(Constant.SHOPPING_BASKET_SESSION_NAME, shoppingBasket);
                System.out.println("create shopping basket");
            }
        }

        System.out.println(shoppingBasket);
        modelMap.addAttribute("shoppingBasket", shoppingBasket);
        modelMap.addAttribute("imageService", imageService);

        return new ModelAndView("forward:/menu/items/shoppingBasket");
    }

    @PostMapping
    @Transactional
    public ModelAndView add(@ModelAttribute FormItemId itemId, ModelMap modelMap, Principal principal,
                            /*@ModelAttribute("shoppingBasket")*/ /*ShoppingBasket shoppingBasket*/
                            HttpServletRequest request) {
        Item item = itemService.findById(itemId.getId()).orElse(new Item());
        if (item.getCountInStock() == 0) {
            return new ModelAndView("redirect:/", "error", "item is out of stock");
        } else {
            if (principal != null) {
                shoppingBasketService.addToShoppingBasket(
                        userService
                                .fromUserDetailsUser(
                                        (User) ((Authentication) principal).getPrincipal()
                                ),
                        item
                );
            }else{
                shoppingBasketService.addToShoppingBasketUnlogin(item, request.getSession());
                /*ShoppingBasket shoppingBasket = (ShoppingBasket) request.getSession().getAttribute(Constant.SHOPPING_BASKET_SESSION_NAME);
                if(shoppingBasket != null) {
                    if (shoppingBasket.getItems() == null) {
                        shoppingBasket.setItems(new ArrayList<Item>());
                    }
                }else{
                    shoppingBasket =  new ShoppingBasket((long) 0, new com.azino.project.model.User(), new ArrayList<Item>(), BigDecimal.ZERO);
                    System.out.println("create shopping basket");
                    *//*shoppingBasket.getItems().add(item);*//*
                }
                shoppingBasket.getItems().add(item);
                shoppingBasket.setAmount(shoppingBasket.getAmount().add(item.getPrice()));
                request.getSession().setAttribute(Constant.SHOPPING_BASKET_SESSION_NAME, shoppingBasket);*/
            }
            return new ModelAndView("redirect:/shoppingBasket");
            /*return getSBPage(modelMap, principal, shoppingBasket);*/
        }
    }

    @DeleteMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public HttpStatus delete(@RequestBody DeleteFormShoppingBasket deleteFormShoppingBasket, Principal principal,
                             /*@ModelAttribute("shoppingBasket") ShoppingBasket shoppingBasket*/
                             HttpServletRequest request) {
        ShoppingBasket shoppingBasket = null;
        if(principal != null) {
            shoppingBasketService
                    .deleteItemFromShoppingBasket(
                            deleteFormShoppingBasket.getItemId(),
                            deleteFormShoppingBasket.getShoppingBasketId()
                    );
        }else{
            /*Optional<Item> item = itemService.findById(deleteFormShoppingBasket.getItemId());
            if(item.isPresent()) {
                shoppingBasket = (ShoppingBasket) request.getSession().getAttribute(Constant.SHOPPING_BASKET_SESSION_NAME);
                if(null != shoppingBasket && null != shoppingBasket.getItems()) {
                    if (shoppingBasket.getItems().contains(item.get())) {
                        shoppingBasket.getItems().remove(item.get());
                        shoppingBasket.setAmount(shoppingBasket.getAmount().subtract(item.get().getPrice()));
                    }
                    request.getSession().setAttribute(Constant.SHOPPING_BASKET_SESSION_NAME, shoppingBasket);
                }else{
                    return HttpStatus.BAD_REQUEST;
                }
            }*/
            if(!shoppingBasketService.deleteFromShoppingBasketUnlogin(deleteFormShoppingBasket.getItemId(), request.getSession())){
                return HttpStatus.BAD_REQUEST;
            }
        }
        return HttpStatus.OK;
    }
}
