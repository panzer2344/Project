package com.azino.project.controller;

import com.azino.project.model.DTO.form.FormItem;
import com.azino.project.model.DTO.form.FormItemWithoutImage;
import com.azino.project.model.Item;
import com.azino.project.service.ImageService;
import com.azino.project.service.ItemService;
import com.azino.project.service.MenuService;
import com.azino.project.service.UserService;
import com.azino.project.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
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

    @Autowired
    private MenuService menuService;

    /*@Autowired
    private CategoryService categoryService;*/

    @GetMapping("addPage")
    public ModelAndView getAddPage(ModelMap modelMap) {
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
    public ModelAndView add(@Valid @ModelAttribute FormItem formItem, BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("items/addItem");
            modelAndView.addObject("errors", bindingResult.getFieldErrors());
            menuService.addCategoriesToMenu(modelAndView);
            return modelAndView;
            //redirectAttributes.addAllAttributes(bindingResult.getModel());
            //return new ModelAndView("redirect:/menu/items/addItem");
        }
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
    public ModelAndView getAll(Model model) {
        Iterable<Item> items = itemService.findAll();
        model.addAttribute("items", items);
        model.addAttribute("imageService", imageService);
        return new ModelAndView("items/itemsPage");
    }


    @DeleteMapping("/{itemId}")
    public String delete(@PathVariable("itemId") Long id, HttpServletResponse response) {
        Boolean isMightBeDeleted = itemService.isItemInPurchases(id);
        if (isMightBeDeleted) {
            response.setStatus(HttpServletResponse.SC_OK);
            itemService.deleteById(id);
            //itemService.deleteById(id);
            return "Item " + id + " deleted";
        }
        /*response.setStatus(HttpServletResponse.SC_);*/
        return "Item " + id + " cannot be deleted";
    }

    @GetMapping("update/{id}")
    public ModelAndView getUpdatePage(ModelMap modelMap, @PathVariable Long id) {
        modelMap.addAttribute(itemService.findById(id).get());
        return new ModelAndView("forward:/menu/items/update");
    }

    @PutMapping("update/{id}")
    @Transactional
    public String update(@PathVariable Long id, @Valid @RequestBody FormItemWithoutImage formItem, BindingResult bindingResult, HttpServletResponse response, Principal principal) {
        if (bindingResult.hasErrors()) {
            /*StringBuilder error = new StringBuilder();
            bindingResult.getFieldErrors().forEach(
                    fieldError -> error
                            .append(fieldError.getField())
                            .append(" ")
                            .append(fieldError.getDefaultMessage())
                            .append("\n")
            );*/
            StringBuilder error = WebUtils.bindingResultErrorsToString(bindingResult);
            try {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, error.toString());
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
            finally {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return "Error!";
            }
        }
        Item item = itemService.findById(id).get();
        /*if (formItem.getAvatar() != null) {
            item.setAvatar(
                    imageService.save(formItem.getAvatar(), userService
                            .fromUserDetailsUser((User) ((Authentication) principal).getPrincipal()))
            );
        }*/
        if (formItem.getCategories() != null) {
            item.setCategories(formItem.getCategories());
        }
        if (formItem.getCountInStock() != null) {
            item.setCountInStock(formItem.getCountInStock());
        }
        if (formItem.getPrice() != null) {
            item.setPrice(formItem.getPrice());
        }
        if (formItem.getName() != null) {
            item.setName(formItem.getName());
        }
        return "Updated!";
    }

}
