package com.azino.project.controller;

import com.azino.project.model.Item;
import com.azino.project.service.ImageService;
import com.azino.project.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("search")
public class SearchController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ImageService imageService;

    @GetMapping
    public ModelAndView getItemsByName(@RequestParam("text") String name, ModelMap modelMap){
        /*List<Item> items = itemService.findByName(name);
        modelMap.addAttribute("items", items);
        modelMap.addAttribute("imageService", imageService);
        return new ModelAndView("forward:/menu/index");*/
        modelMap.addAttribute("items", itemService.findByName(name));
        return new ModelAndView("forward:/filtered");
    }

    @GetMapping("filter/price")
    public ModelAndView getItemsWithPriceFilter(@RequestParam(value = "from", defaultValue = "0.0") Double from,
                                                @RequestParam(value = "to", defaultValue = "" + Double.MAX_VALUE ) Double to,
                                                ModelMap modelMap){
        /*List<Item> items = itemService.findItemsWithPriceFilter(from, to);*/
        /*modelMap.addAttribute("items", items);*/
        /*modelMap.addAttribute("imageService", imageService);*/
        modelMap.addAttribute("items", itemService.findItemsWithPriceFilter(from, to));
        return new ModelAndView("forward:/filtered");
        /*return new ModelAndView("forward:/menu/index");*/
    }

}
