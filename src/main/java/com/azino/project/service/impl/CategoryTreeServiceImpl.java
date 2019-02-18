package com.azino.project.service.impl;

import com.azino.project.model.Category;
import com.azino.project.model.CategoryTree;
import com.azino.project.repository.CategoryTreeRepository;
import com.azino.project.service.CategoryTreeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CategoryTreeServiceImpl
        extends BaseServiceImpl<CategoryTree, CategoryTreeRepository>
        implements CategoryTreeService {

    public CategoryTreeServiceImpl(CategoryTreeRepository categoryTreeRepository) {
        super(categoryTreeRepository);
    }

    @Override
    public Iterable<Category> findAllDescendants(Category category) {
        return repository.findAllDescendants(category);
    }

    @Override
    public Iterable<Category> findAllDescendants(Long id) {
        return repository.findAllDescendants(id);
    }

    @Override
    public Iterable<Category> findImmediateDescendants(Category category) {
        return repository.findImmediateDescendants(category);
    }

    @Override
    public Iterable<Category> findImmediateDescendants(Long id) {
        return repository.findImmediateDescendants(id);
    }

    @Override
    public void addImmediateDescendant(Category category, Category parent) {
        Iterable<CategoryTree> parentNodes = super.repository.findAllByDescendant(parent);
        List<CategoryTree> newNodes = new ArrayList<>();
        for(CategoryTree pn : parentNodes){
            CategoryTree categoryTree = new CategoryTree((long) 0, pn.getAncestor(), category, pn.getLevel() + 1);
            newNodes.add(categoryTree);
        }
        CategoryTree categoryTree = new CategoryTree((long) 0, category, category, newNodes.get(0).getLevel() + 1);
        newNodes.add(categoryTree);
        saveAll(newNodes);
    }

    @Override
    public Iterable<Category> findAllParents(Category category) {
        return super.repository.findAllParents(category);
    }

    @Override
    public Iterable<Category> findAllParents(Long id) {
        return super.repository.findAllParents(id);
    }

    @Override
    public Iterable<CategoryTree> findAllByDescendant(Category category) {
        return super.repository.findAllByDescendant(category);
    }

    @Override
    public Iterable<CategoryTree> findAllByDescendantId(Long id) {
        return super.repository.findAllByDescendant_Id(id);
    }

    @Override
    public Category findImmediateParent(Long id) {
        return repository.findImmediateParent(id);
    }

    @Override
    public Iterable<CategoryTree> findAllByAncestorId(Long id) {
        return repository.findAllByAncestor_Id(id);
    }
}
