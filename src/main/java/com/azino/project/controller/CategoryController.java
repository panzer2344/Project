package com.azino.project.controller;

import com.azino.project.model.Category;
import com.azino.project.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /*@Autowired
    private CategoryTreeService categoryTreeService;*/

    @GetMapping("addPage")
    public ModelAndView getAddPage(ModelMap modelMap) {
        return new ModelAndView("forward:/menu/categories/addCategory");
    }

    @PostMapping
    public ModelAndView add(@RequestParam(value = "name", required = true) String name,
                            @RequestParam(value = "category", required = false) Category category) {
        if (category != null) {
            categoryService.add(name, category);
        } else {
            categoryService.add(name);
        }
        return new ModelAndView("redirect:/account");
    }

    @GetMapping("delete")
    public ModelAndView getDeletePage() {
        return new ModelAndView("forward:/menu/categories/deleteCategory");
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable Long id) {
        Category category = categoryService.findById(id).get();
        categoryService.delete(id);
        return "Ok";
    }

    @GetMapping("update")
    public ModelAndView getUpdatePage() {
        return new ModelAndView("forward:/menu/categories/update");
    }

    @PutMapping("{id}")
    @Transactional
    public String update(@RequestBody Category category, @PathVariable Long id) {
        Category newCategory = categoryService.findById(id).get();
        newCategory.setName(category.getName());
        return "Updated!";
    }

}
