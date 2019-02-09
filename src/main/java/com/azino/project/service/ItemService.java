package com.azino.project.service;

import com.azino.project.model.Category;
import com.azino.project.model.DTO.form.FormItem;
import com.azino.project.model.Item;
import com.azino.project.model.User;

public interface ItemService extends BaseService<Item>{

    Item fromFormItem(ImageService imageService, User user, FormItem item);

    Iterable<Item> findAllByCategoryContains(Category category);

    Iterable<Item> findAllByCategoryContains(Long id);

    Iterable<Item> findItemWithDescendantsByCategoryContains(Category category);

    Iterable<Item> findItemWithDescendantsByCategoryContains(Long id);

}
