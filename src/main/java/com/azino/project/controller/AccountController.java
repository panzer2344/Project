package com.azino.project.controller;

import com.azino.project.model.Category;
import com.azino.project.service.CategoryService;
import com.azino.project.service.ImageService;
import com.azino.project.service.UserService;
import com.azino.project.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.ui.Model;
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

    @Autowired
    private ImageService imageService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ModelAndView accountPage(Model model, Principal principal) {
        /* adding categories to menu navbar */
        Iterable<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        /*main content with user info*/
        if (principal != null) {
            User user = (User) ((Authentication) principal).getPrincipal();
            model.addAttribute("user", userService.fromUserDetailsUser(user));

        }
        return new ModelAndView("users/account");
    }
}
