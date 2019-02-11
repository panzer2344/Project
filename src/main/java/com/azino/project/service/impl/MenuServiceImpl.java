package com.azino.project.service.impl;

import com.azino.project.model.Category;
import com.azino.project.model.CategoryTree;
import com.azino.project.service.CategoryTreeService;
import com.azino.project.service.MenuService;
import org.javatuples.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MenuServiceImpl implements MenuService {

    //@Autowired
    //private CategoryService categoryService;

    @Autowired
    private CategoryTreeService categoryTreeService;

    @Override
    public ModelAndView addCategoriesToMenu(ModelAndView modelAndView) {
        //Iterable<Category> categories = categoryService.findAll();

        /* this map using for to mark level of categories in category multi-level list*/
        /*Map<Long, Integer> categoriesUsedFlags = new HashMap<>();
        for(Category category : categories){
            if(null == category.getParent()) {
                categoriesUsedFlags.put(category.getId(), 0);
            }else{
                categoriesUsedFlags.put(category.getId(), categoriesUsedFlags.get(category.getParent().getId()) + 1);
            }
        }*/

        //modelAndView.getModelMap().addAttribute("categories", categories);
        //modelAndView.getModelMap().addAttribute("categoriesUsedFlags", categoriesUsedFlags);

        Iterable<CategoryTree> categoryTree = categoryTreeService.findAll();

        /* map of immediate descendants for each category*/
        Map<Category, Pair<Integer, List<Category>>> immediateDescendantsMap = new HashMap<>();
        for (CategoryTree categoryNode : categoryTree) {
            if (categoryNode.getAncestor().equals(categoryNode.getDescendant())) {
                immediateDescendantsMap.put(
                        categoryNode.getAncestor(),
                        new Pair<>(
                                categoryNode.getLevel(),
                                new ArrayList<>()
                        )
                );
            }
        }

        for (CategoryTree categoryNode : categoryTree) {
            if (categoryNode.getAncestor() != categoryNode.getDescendant()) {
                Pair<Integer, List<Category>> pair = immediateDescendantsMap.get(categoryNode.getAncestor());
                if (categoryNode.getLevel().equals(pair.getValue0() + 1)) {
                    pair.getValue1().add(categoryNode.getDescendant());
                }
                //immediateDescendantsMap.get(categoryNode.getAncestor()).getValue1().add(categoryNode.getDescendant());
            }
        }

        modelAndView.getModelMap().addAttribute("categoryTree", categoryTree);
        modelAndView.getModelMap().addAttribute("immediateDescendantsMap", immediateDescendantsMap);

        return modelAndView;
    }
}
