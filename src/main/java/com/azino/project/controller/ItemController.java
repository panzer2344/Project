package com.azino.project.controller;

import com.azino.project.model.Item;
import com.azino.project.model.User;
import com.azino.project.model.form.FormItem;
import com.azino.project.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("addPage")
    public ModelAndView getAddPage(){
        return new ModelAndView("items/addItem");
    }

    @PostMapping
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
    }

    @GetMapping
    public ModelAndView getAll(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model){
        Iterable<Item> items = itemService.findAll();
        model.addAttribute("items", items);
        model.addAttribute("imageService", imageService);
        return new ModelAndView("items/itemsPage");
    }

}
