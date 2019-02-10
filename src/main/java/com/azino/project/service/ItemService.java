package com.azino.project.service;

import com.azino.project.model.Category;
import com.azino.project.model.DTO.form.FormItem;
import com.azino.project.model.Item;
import com.azino.project.model.User;

import java.util.List;

public interface ItemService extends BaseService<Item>{

    Item fromFormItem(ImageService imageService, User user, FormItem item);

    /*List<Item> findAllById(List<Long> ids);*/

    Iterable<Item> findAllByCategoryContains(Category category);

    Iterable<Item> findAllByCategoryContains(Long id);

    Iterable<Item> findItemWithDescendantsByCategoryContains(Category category);

    Iterable<Item> findItemWithDescendantsByCategoryContains(Long id);

    List<Item> findAllByShoppingBasketId(Long id);

}
