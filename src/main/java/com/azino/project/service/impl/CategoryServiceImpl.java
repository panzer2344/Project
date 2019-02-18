package com.azino.project.service.impl;

import com.azino.project.model.Category;
import com.azino.project.model.CategoryTree;
import com.azino.project.model.Item;
import com.azino.project.repository.CategoryRepository;
import com.azino.project.service.CategoryService;
import com.azino.project.service.CategoryTreeService;
import com.azino.project.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Transactional
public class CategoryServiceImpl extends BaseServiceImpl<Category, CategoryRepository> implements CategoryService {
    public CategoryServiceImpl(CategoryRepository categoryRepository){
        super(categoryRepository);
    }

    @Autowired
    private CategoryTreeService categoryTreeService;

    @Autowired
    private ItemService itemService;

    @Override
    @Transactional
    public Category add(String name, Category parent) {
        Category category = new Category((long) 0, name);
        category = save(category);
        categoryTreeService.addImmediateDescendant(category, parent);
        return category;
    }

    @Override
    public Category findFirstByName(String name) {
        return super.repository.getFirstByName(name);
    }

    @Override
    public Category add(String name) {
        Category category = new Category((long) 0, name);
        category = save(category);
        CategoryTree categoryTree = new CategoryTree((long) 0, category, category, 0);
        categoryTreeService.save(categoryTree);
        return category;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Iterable<Item> items = itemService.findAllByCategoryContains(id);
        Category category = findById(id).get();
        Category parentCategory = categoryTreeService.findImmediateParent(id);
        for(Item item : items){
            Set<Category> categories = item.getCategories();
            categories.remove(category);
            if(!categories.contains(parentCategory)){
                categories.add(parentCategory);
            }
            item.setCategories(categories);
        }
        Iterable<CategoryTree> childs = categoryTreeService.findAllByAncestorId(id);
        for(CategoryTree categoryTree : childs) {
            Iterable<CategoryTree> childsRows = categoryTreeService.findAllByDescendantId(categoryTree.getDescendant().getId());
            for(CategoryTree childCT : childsRows){
                childCT.setLevel(childCT.getLevel() - 1);
            }
        }
        categoryTreeService.deleteAll(childs);
        Iterable<CategoryTree> categoryTrees = categoryTreeService.findAllByDescendantId(id);
        categoryTreeService.deleteAll(categoryTrees);
        /*categoryTrees = categoryTreeService.findAllByAncestorId(id);
        categoryTreeService.deleteAll(categoryTrees);*/
        delete(category);
    }
}
