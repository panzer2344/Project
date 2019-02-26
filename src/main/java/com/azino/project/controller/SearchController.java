package com.azino.project.controller;

import com.azino.project.constants.Constant;
import com.azino.project.service.ImageService;
import com.azino.project.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;

@RestController
@RequestMapping("search")
public class SearchController {

    @Autowired
    private ItemService itemService;

    @GetMapping
    public ModelAndView getItemsByName(@RequestParam("text") String name, ModelMap modelMap){
        //modelMap.addAttribute("items", itemService.findByName(name));
        modelMap.addAttribute("items", itemService.findByNameContaining(name));
        return new ModelAndView("forward:/filtered");
    }

    @GetMapping("filter/price")
    public ModelAndView getItemsWithPriceFilter(@RequestParam(value = "from", defaultValue = "0") BigDecimal from,
                                                @RequestParam(value = "to", defaultValue = "" + Integer.MAX_VALUE) BigDecimal to,
                                                ModelMap modelMap){
        modelMap.addAttribute("items", itemService.findItemsWithPriceFilter(from, to));
        return new ModelAndView("forward:/filtered");
    }

}
