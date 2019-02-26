package com.azino.project.controller;

import com.azino.project.constants.Constant;
import com.azino.project.model.ShoppingBasket;
import com.azino.project.service.ShoppingBasketService;
import com.azino.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private ShoppingBasketService shoppingBasketService;

    @GetMapping
    public ModelAndView getLoginPage(ModelMap model) {
        return new ModelAndView("forward:/menu/users/login");
    }


    /*todo: if valid, move session shopping basket to db shopping basket*/
    @PostMapping()
    public void login(@RequestParam("username") String username, @RequestParam("password") String password, HttpServletResponse response, HttpServletRequest request) {
        try {
            System.out.println("hello");

            if (userService.isValid(username, password)) {

                /*ShoppingBasket shoppingBasket = shoppingBasketService.getShoppingBasketFromSession(request.getSession());
                shoppingBasket.setUser(userService.findByName(username));
                shoppingBasketService.save(shoppingBasket);*/

                if (request.getSession().getAttribute(Constant.SHOPPING_BASKET_SESSION_NAME) != null) {
                    shoppingBasketService.saveFromUnloginToDatabase(request.getSession(), username);
                    //request.getSession().invalidate();
                    request.getSession().setAttribute(Constant.SHOPPING_BASKET_SESSION_NAME, null);
                }

                System.out.println("hi");
                request.login(username, password);
                HttpSession session = request.getSession(true);
                userService.addSessionToActiveSessions(username, session.getId());
                response.sendRedirect("/");
            } else {
                System.out.println("fuck");
            }
            //return new ModelAndView("redirect:/j_spring_security_check");
            //request.login(username, password);
            //response.sendRedirect("/j_spring_security_check");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (ServletException se) {
            se.printStackTrace();
        }
    }

    /*@GetMapping("success")
    public ModelAndView login(HttpServletRequest request){
        request.getSession().invalidate();
        return new ModelAndView("redirect:/loginSecurity");
    }*/
}
