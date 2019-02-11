package com.azino.project.service.impl;

import com.azino.project.model.Category;
import com.azino.project.model.CategoryTree;
import com.azino.project.repository.CategoryTreeRepository;
import com.azino.project.service.CategoryTreeService;
import org.springframework.stereotype.Service;

@Service
public class CategoryTreeServiceImpl
        extends BaseServiceImpl<CategoryTree, CategoryTreeRepository>
        implements CategoryTreeService {

    public CategoryTreeServiceImpl(CategoryTreeRepository categoryTreeRepository) {
        super(categoryTreeRepository);
    }

    @Override
    public Iterable<Category> findAllDescendants(Category category) {
        return repository.findAllDescendants(category);
    }

    @Override
    public Iterable<Category> findAllDescendants(Long id) {
        return repository.findAllDescendants(id);
    }

    @Override
    public Iterable<Category> findImmediateDescendants(Category category) {
        return repository.findImmediateDescendants(category);
    }

    @Override
    public Iterable<Category> findImmediateDescendants(Long id) {
        return repository.findImmediateDescendants(id);
    }
}
