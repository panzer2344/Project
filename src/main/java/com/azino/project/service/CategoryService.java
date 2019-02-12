package com.azino.project.service;

import com.azino.project.model.Category;

public interface CategoryService extends BaseService<Category> {

    Category add(String name, Category parent);

    Category add(String name);

    Category findFirstByName(String name);
}
