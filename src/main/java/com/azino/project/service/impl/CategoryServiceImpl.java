package com.azino.project.service.impl;

import com.azino.project.model.Category;
import com.azino.project.repository.CategoryRepository;
import com.azino.project.service.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends BaseServiceImpl<Category, CategoryRepository> implements CategoryService {
    public CategoryServiceImpl(CategoryRepository categoryRepository){
        super(categoryRepository);
    }
}
