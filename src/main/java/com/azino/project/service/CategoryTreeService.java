package com.azino.project.service;

import com.azino.project.model.Category;
import com.azino.project.model.CategoryTree;

public interface CategoryTreeService extends BaseService<CategoryTree> {

    Iterable<Category> findImmediateDescendants(Long id);

    Iterable<Category> findAllDescendants(Category category);

    Iterable<Category> findAllDescendants(Long id);

    Iterable<Category> findImmediateDescendants(Category category);

}
