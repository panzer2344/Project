package com.azino.project.controller;

import com.azino.project.model.Category;
import com.azino.project.model.Role;
import com.azino.project.service.CategoryService;
import com.azino.project.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("signUp")
public class SignUpController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ModelAndView getSignUpPage(Model model){
        /*adding categories to menu navbar*/
        Iterable<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        /*main content needed roles*/
        Iterable<Role> roles = roleService.findAll();
        return new ModelAndView("users/registration", "roles", roles);
    }

}
