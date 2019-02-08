package com.azino.project.controller;

import com.azino.project.model.Role;
import com.azino.project.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("signUp")
public class SignUpController {

    @Autowired
    private RoleService roleService;

    @GetMapping
    public ModelAndView getSignUpPage(ModelMap model) {
        Iterable<Role> roles = roleService.findAll();
        model.addAttribute("roles", roles);
        return new ModelAndView("forward:/menu/users/registration", model);
    }

}
