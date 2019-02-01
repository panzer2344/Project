package com.azino.project.controller;

import com.azino.project.model.Category;
import com.azino.project.model.Item;
import com.azino.project.service.CategoryService;
import com.azino.project.service.ImageService;
import com.azino.project.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController
@RequestMapping("")
public class IndexController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    public ModelAndView index(Model model){
        Iterable<Item> items = itemService.findAll();
        Iterable<Category> categories = categoryService.findAll();

        model.addAttribute("items", items);
        model.addAttribute("imageService", imageService);
        model.addAttribute("categories", categories);

    return new ModelAndView("index");
    }

    @GetMapping("{category}")
    public ModelAndView index(@PathVariable Long categoryId, Model model){
        Category category = categoryService.findById(categoryId).get();
        Iterable<Item> items = itemService.findAllByCategoryContains(category);
        Iterable<Category> categories = categoryService.findAll();

        model.addAttribute("items", items);
        model.addAttribute("imageService", imageService);
        model.addAttribute("categories", categories);

        return new ModelAndView("index");
    }
}
