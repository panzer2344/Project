package com.azino.project.controller;

import com.azino.project.model.Item;
import com.azino.project.service.ImageService;
import com.azino.project.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
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

    @GetMapping("")
    public ModelAndView index(ModelMap model) {
        Iterable<Item> items = itemService.findAll();

        model.addAttribute("items", items);
        model.addAttribute("imageService", imageService);

        return new ModelAndView("forward:/menu/index", model);
    }

    @GetMapping("{categoryId}")
    public ModelAndView index(@PathVariable Long categoryId, ModelMap modelMap) {
        //Iterable<Item> items = itemService.findAllByCategoryContains(categoryId);
        Iterable<Item> items = itemService.findItemWithDescendantsByCategoryContains(categoryId);
        modelMap.addAttribute("items", items);
        modelMap.addAttribute("imageService", imageService);
        return new ModelAndView("forward:/menu/index", modelMap);
    }
}
