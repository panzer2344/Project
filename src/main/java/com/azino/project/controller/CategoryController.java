package com.azino.project.controller;

import com.azino.project.model.Category;
import com.azino.project.service.CategoryService;
import com.azino.project.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Optional;

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
                            @RequestParam(value = "category", required = false) Category category,
                            RedirectAttributes redirectAttributes) {
        if (name.length() < Category.NAME_MIN_LENGTH || name.length() > Category.NAME_MAX_LENGTH) {
            redirectAttributes.addAttribute("error", "name length must be in range " + Category.NAME_MIN_LENGTH + " to " + Category.NAME_MAX_LENGTH);
            return new ModelAndView("redirect:/menu/categories/addCategory");
        }
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
        Optional<Category> categoryOptional = categoryService.findById(id);
        if (categoryOptional.isPresent()) {
            categoryService.delete(id);
            return "Ok";
        } else {
            return "No object";
        }
    }

    @GetMapping("update")
    public ModelAndView getUpdatePage() {
        return new ModelAndView("forward:/menu/categories/update");
    }

    @PutMapping("{id}")
    @Transactional
    public String update(@Valid @RequestBody Category category, BindingResult bindingResult, @PathVariable Long id, HttpServletResponse response) {
        try {
            if(id < 0){
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Id must be greater than zero!");
                return "Error!";
            }
            if (bindingResult.hasErrors()) {
                StringBuilder error = WebUtils.bindingResultErrorsToString(bindingResult);
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, error.toString());
                return "Error!";
            }
            Optional<Category> newCategoryOptional = categoryService.findById(id);
            if (newCategoryOptional.isPresent()) {
                newCategoryOptional.get().setName(category.getName());
                return "Updated!";
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "No object with this id!");
                return "Error!";
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return "Error!";
    }
}
