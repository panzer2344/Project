package com.azino.project.controller;

import com.azino.project.model.Category;
import com.azino.project.model.CategoryTree;
import com.azino.project.service.CategoryService;
import com.azino.project.service.CategoryTreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /*@Autowired
    private CategoryTreeService categoryTreeService;*/

    @GetMapping("addPage")
    public ModelAndView getAddPage(ModelMap modelMap){
        return new ModelAndView("forward:/menu/categories/addCategory");
    }

    @PostMapping
    public ModelAndView add(@RequestParam(value = "name", required = true) String name,
                            @RequestParam(value = "category", required = false) Category category){
        categoryService.add(name, category);
        return new ModelAndView("redirect:/account");
    }

    /*@DeleteMapping
    public ModelAndView delete(){

    }*/



}
