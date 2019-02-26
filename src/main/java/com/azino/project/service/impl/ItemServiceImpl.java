package com.azino.project.service.impl;

import com.azino.project.model.Category;
import com.azino.project.model.DTO.form.FormItem;
import com.azino.project.model.Item;
import com.azino.project.model.User;
import com.azino.project.repository.CategoryTreeRepository;
import com.azino.project.repository.ItemRepository;
import com.azino.project.service.CategoryService;
import com.azino.project.service.ImageService;
import com.azino.project.service.ItemService;
import com.azino.project.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl extends BaseServiceImpl<Item, ItemRepository> implements ItemService {

    @Autowired
    private CategoryTreeRepository categoryTreeRepository;

    @Autowired
    private PurchaseService purchaseService;

    public ItemServiceImpl(ItemRepository itemRepository){
        super(itemRepository);
    }

    public Item fromFormItem(ImageService imageService, User user, FormItem item){
        return new Item(
                item.getName(),
                imageService.save(item.getAvatar(), user),
                item.getPrice(),
                item.getCountInStock(),
                item.getCategories());
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
        //return super.repository.findAllByCategoriesContains(categories);
        return super.repository.findAllByCategoriesContains(categories);
    }

    @Override
    public Iterable<Item> findItemWithDescendantsByCategoryContains(Long id) {
        Iterable<Category> categories = categoryTreeRepository.findAllDescendants(id);
        return super.repository.findAllByCategoriesContains(categories);
    }

    /*@Override
    public List<Item> findAllById(List<Long> ids) {
        return super.repository.findAllById(ids);
    }*/

    @Override
    public List<Item> findAllByShoppingBasketId(Long id) {
        Iterable<Item> items = super.repository.findAllByShoppingBasketId(id);
        List<Item> itemList = new ArrayList<>();
        items.forEach(itemList::add);
        return itemList;
    }

    @Override
    public Boolean isItemInPurchases(Item item) {
        return purchaseService
                .findAllPurchasesByItem(item)
                .isEmpty();
    }

    @Override
    public Boolean isItemInPurchases(Long id) {
        return purchaseService
                .findAllPurchasesByItemId(id)
                .isEmpty();
    }

    @Override
    public List<Item> findByName(String name) {
        List<Item> items = new ArrayList<>();
        repository
                .findByName(name)
                .forEach(items::add);
        return items;
    }

    @Override
    public List<Item> findItemsWithPriceFilter(BigDecimal from, BigDecimal to) {
        List<Item> items = new ArrayList<>();
        repository
                .findWithPriceFilter(from, to)
                .forEach(items::add);
        return items;
    }

    @Override
    public List<Item> findByNameContaining(String name) {
        List<Item> items = new ArrayList<>();
        repository
                .findByNameContaining(name)
                .forEach(items::add);
        return items;
    }

    @Override
    @Transactional
    public Boolean increaseCountInStock(Long id, Integer onValue) {
        Optional<Item> itemOptional = findById(id);
        if(itemOptional.isPresent()){
            Item item = itemOptional.get();
            item.setCountInStock(item.getCountInStock() + onValue);
            return true;
        }
        return false;
    }
}
