package com.azino.project.controller;

import com.azino.project.service.CategoryService;
import com.azino.project.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@RestController
@RequestMapping("menu")
public class MenuController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private MenuService menuService;

    @GetMapping({"{viewDir}/{viewName}", "{viewName}"})
    public ModelAndView pageWithMenu(ModelMap model, @PathVariable String viewName, @PathVariable Optional<String> viewDir) {
        String resultViewName = viewName;
        if (viewDir.isPresent()) {
            resultViewName = viewDir.get() + '/' + viewName;
        }
        ModelAndView modelAndView = new ModelAndView(resultViewName, model);
        menuService.addCategoriesToMenu(modelAndView);
        return modelAndView;
    }

}
