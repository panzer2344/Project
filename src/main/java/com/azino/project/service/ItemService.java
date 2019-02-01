package com.azino.project.service;

import com.azino.project.model.Category;
import com.azino.project.model.Item;
import com.azino.project.model.User;
import com.azino.project.model.form.FormItem;

public interface ItemService extends BaseService<Item>{

    Item fromFormItem(ImageService imageService, User user, FormItem item);

    Iterable<Item> findAllByCategoryContains(Category category);

}
