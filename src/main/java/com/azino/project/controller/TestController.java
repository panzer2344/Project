package com.azino.project.controller;

import com.azino.project.model.Item;
import com.azino.project.service.ImageService;
import com.azino.project.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class TestController {

    @Autowired
    private ImageService imageService;

    @Autowired
    private ItemService itemService;

    @GetMapping("usersTest")
    public ModelAndView usersTest(){
        return new ModelAndView("users/usersTest");
    }

    @GetMapping("BStest")
    public ModelAndView bsTest(){
        return new ModelAndView("index");
    }

    /*@GetMapping("itemsBStest")
    public ModelAndView itemsBStest(Model model){
        Iterable<Item> items = itemService.findAll();
        model.addAttribute("items", items);
        model.addAttribute("imageService", imageService);
        return new ModelAndView("index");
    }*/

    @GetMapping("old_menu")
    public ModelAndView index(){
        return new ModelAndView("old_menu", "name", "Alex");
    }
}
