package com.azino.project.controller;

import com.azino.project.model.DTO.form.FormItem;
import com.azino.project.model.Item;
import com.azino.project.service.ImageService;
import com.azino.project.service.ItemService;
import com.azino.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@RestController
@RequestMapping("items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private UserService userService;

    /*@Autowired
    private CategoryService categoryService;*/

    @GetMapping("addPage")
    public ModelAndView getAddPage(Model model) {
        /*Iterable<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);*/
        return new ModelAndView("forward:/menu/items/addItem");
    }

    /*@PostMapping
    public String add(@ModelAttribute FormItem formItem){
        Item item = itemService
                .fromFormItem(
                        imageService,
                        userService
                                .findById((long)1)
                                .orElse(new User()),
                        formItem
                );
        itemService.save(item);

        return "Item " + item.toString() + " was successfully saved";
    }*/

    @PostMapping
    public ModelAndView add(@ModelAttribute FormItem formItem, Principal principal) {
        User userDetails = (User) ((Authentication) principal).getPrincipal();
        Item item = itemService
                .fromFormItem(
                        imageService,
                        userService.fromUserDetailsUser(userDetails),
                        formItem
                );
        itemService.save(item);
        return new ModelAndView("redirect:/");
    }

    @GetMapping
    public ModelAndView getAll(Model model){
        Iterable<Item> items = itemService.findAll();
        model.addAttribute("items", items);
        model.addAttribute("imageService", imageService);
        return new ModelAndView("items/itemsPage");
    }

}
