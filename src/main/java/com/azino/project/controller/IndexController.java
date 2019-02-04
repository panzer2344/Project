package com.azino.project.controller;

import com.azino.project.model.Category;
import com.azino.project.model.Item;
import com.azino.project.service.CategoryService;
import com.azino.project.service.ImageService;
import com.azino.project.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.ListUtils;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;


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
    @Transactional
    public ModelAndView index(Model model){
        /*Category category = new Category((long)0, "Category 2");
        categoryService.save(category);*/

        Iterable<Item> items = itemService.findAll();
        Iterable<Category> categories = categoryService.findAll();

        /*Iterator<Item> itemIterator = items.iterator();
        Object[] categoriesArray = ((Collection<?>)categories).toArray();

        for(Object object : categoriesArray){
            System.out.println((Category) object);
        }

        for(int i = 0; i < ((Collection<?>)items).size() / 2; i++){
            if(itemIterator.hasNext()) {
                Item item = itemIterator.next();
                System.out.println("1 " + item);
                item.setCategories(new HashSet<Category>(){{
                    add((Category) categoriesArray[0]);
                }});
            }
        }

        for(int i = ((Collection<?>)items).size() / 2; i < ((Collection<?>)items).size(); i++){
            if(itemIterator.hasNext()) {
                Item item = itemIterator.next();
                System.out.println("2 " + item);
                item.setCategories(new HashSet<Category>(){{
                    add((Category) categoriesArray[1]);
                }});
            }
        }

        System.out.println("3");

        for(Item item : itemService.findAll()){
            System.out.println("op");
            Object[] array = item.getCategories().toArray();
            for(Object object : array){
                System.out.println("tr" + (Category)object);
            }
        }*/

        model.addAttribute("items", items);
        model.addAttribute("imageService", imageService);
        model.addAttribute("categories", categories);

    return new ModelAndView("index");
    }

    @GetMapping("{categoryId}")
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
