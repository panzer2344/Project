package com.azino.project.controller;

import com.azino.project.model.Category;
import com.azino.project.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ModelAndView getLoginPage(Model model){
        /*adding categories to menu navbar*/
        Iterable<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        /*returning view with models*/
        return new ModelAndView("users/login");
    }

}
