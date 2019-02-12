package com.azino.project.service;

import com.azino.project.model.Category;
import com.azino.project.model.CategoryTree;

public interface CategoryTreeService extends BaseService<CategoryTree> {

    Iterable<Category> findImmediateDescendants(Long id);

    Iterable<Category> findAllDescendants(Category category);

    Iterable<Category> findAllDescendants(Long id);

    Iterable<Category> findImmediateDescendants(Category category);

    void addImmediateDescendant(Category category, Category parent);

    Iterable<Category> findAllParents(Category category);

    Iterable<Category> findAllParents(Long id);

    Iterable<CategoryTree> findAllByDescendant(Category category);

    Iterable<CategoryTree> findAllByDescendantId(Long id);

}
