package com.azino.project.service;

import com.azino.project.model.Category;
import com.azino.project.repository.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends BaseServiceImpl<Category, CategoryRepository> implements CategoryService {
    public CategoryServiceImpl(CategoryRepository categoryRepository){
        super(categoryRepository);
    }
}
