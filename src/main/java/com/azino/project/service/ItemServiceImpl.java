package com.azino.project.service;

import com.azino.project.model.Category;
import com.azino.project.model.Item;
import com.azino.project.model.User;
import com.azino.project.model.form.FormItem;
import com.azino.project.repository.ItemRepository;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl extends BaseServiceImpl<Item, ItemRepository> implements ItemService {

    public ItemServiceImpl(ItemRepository itemRepository){
        super(itemRepository);
    }

    public Item fromFormItem(ImageService imageService, User user, FormItem item){
        return new Item(item.getName(), imageService.save(item.getAvatar(), user), item.getPrice());
    }

    @Override
    public Iterable<Item> findAllByCategoryContains(Category category) {
        return ((ItemRepository)super.repository).findAllByCategoriesContains(category);
    }
}
