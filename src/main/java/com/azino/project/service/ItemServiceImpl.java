package com.azino.project.service;

import com.azino.project.model.Category;
import com.azino.project.model.DTO.form.FormItem;
import com.azino.project.model.Item;
import com.azino.project.model.User;
import com.azino.project.repository.CategoryTreeRepository;
import com.azino.project.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl extends BaseServiceImpl<Item, ItemRepository> implements ItemService {

    @Autowired
    CategoryTreeRepository categoryTreeRepository;

    public ItemServiceImpl(ItemRepository itemRepository){
        super(itemRepository);
    }

    public Item fromFormItem(ImageService imageService, User user, FormItem item){
        return new Item(item.getName(), imageService.save(item.getAvatar(), user), item.getPrice(), item.getCategories());
    }

    @Override
    public Iterable<Item> findAllByCategoryContains(Category category) {
        return ((ItemRepository)super.repository).findAllByCategoriesContains(category);
    }

    @Override
    public Iterable<Item> findAllByCategoryContains(Long id) {
        //return ((ItemRepository)super.repository).findAllByCategoriesContainsWithId(id);
        return repository.findAllByCategoriesContains(id);
    }

    @Override
    public Iterable<Item> findItemWithDescendantsByCategoryContains(Category category) {
        Iterable<Category> categories = categoryTreeRepository.findAllDescendants(category);
        return super.repository.findAllByCategoriesContains(categories);
    }

    @Override
    public Iterable<Item> findItemWithDescendantsByCategoryContains(Long id) {
        Iterable<Category> categories = categoryTreeRepository.findAllDescendants(id);
        return super.repository.findAllByCategoriesContains(categories);
    }
}
