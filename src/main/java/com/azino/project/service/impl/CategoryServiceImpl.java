package com.azino.project.service.impl;

import com.azino.project.model.Category;
import com.azino.project.repository.CategoryRepository;
import com.azino.project.service.CategoryService;
import com.azino.project.service.CategoryTreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CategoryServiceImpl extends BaseServiceImpl<Category, CategoryRepository> implements CategoryService {
    public CategoryServiceImpl(CategoryRepository categoryRepository){
        super(categoryRepository);
    }

    @Autowired
    private CategoryTreeService categoryTreeService;

    @Override
    @Transactional
    public Category add(String name, Category parent) {
        Category category = new Category((long) 0, name);
        category = save(category);
        categoryTreeService.addImmediateDescendant(category, parent);
        return category;
    }

    @Override
    public Category findFirstByName(String name) {
        return super.repository.getFirstByName(name);
    }
}
