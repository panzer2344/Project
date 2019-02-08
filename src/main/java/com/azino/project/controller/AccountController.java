package com.azino.project.controller;

import com.azino.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@RestController
@RequestMapping("account")
public class AccountController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ModelAndView accountPage(ModelMap model, Principal principal) {
        if (principal != null) {
            User user = (User) ((Authentication) principal).getPrincipal();
            model.addAttribute("user", userService.fromUserDetailsUser(user));

        }
        return new ModelAndView("forward:menu/users/account", model);
    }
}
