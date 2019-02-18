package com.azino.project.repository;

import com.azino.project.model.Category;
import com.azino.project.model.CategoryTree;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface CategoryTreeRepository extends CrudRepository<CategoryTree, Long> {

    @Query("select c from CategoryTree ct join Category c on ct.descendant.id = c.id where ct.ancestor.id = :id")
    Iterable<Category> findAllDescendants(@Param("id") Long id);

    @Query("select c from CategoryTree ct join Category c on ct.descendant.id = c.id where ct.ancestor = :category")
    Iterable<Category> findAllDescendants(@Param("category") Category category);

    @Query("select c from CategoryTree ct join Category c on ct.descendant.id = c.id where ct.ancestor.id = :id" +
            " and ct.level = (select ct1.level + 1 from CategoryTree ct1 where ct1.ancestor = ct1.descendant)")
    Iterable<Category> findImmediateDescendants(@Param("id") Long id);

    @Query("select c from CategoryTree ct join Category c on ct.descendant.id = c.id where ct.ancestor = :category" +
            " and ct.level = (select ct1.level + 1 from CategoryTree ct1 where ct1.ancestor = ct1.descendant)")
    Iterable<Category> findImmediateDescendants(@Param("category") Category category);

    /*@Query("select c, ct.level from CategoryTree ct join Category c on ct.descendant.id = c.id where ct.ancestor.id = :id" +
            " and ct.level = (select ct1.level + 1 from CategoryTree ct1 where ct1.ancestor = ct1.descendant)")
    Iterable<List<?>> findImmediateDescendantsWithDescedantLevel(@Param("id") Long id);

    @Query("select c, ct.level from CategoryTree ct join Category c on ct.descendant.id = c.id where ct.ancestor = :category" +
            " and ct.level = (select ct1.level + 1 from CategoryTree ct1 where ct1.ancestor = ct1.descendant)")
    Iterable<List<?>> findImmediateDescendantsWithDescedantLevel(@Param("category") Category category);  */

    /*@Query("insert into CategoryTree")
    void addImmediateDescendant(@Param("category") Category category);*/

    @Query("select ct.ancestor from CategoryTree ct join Category c on ct.descendant.id = c.id where ct.descendant = :category")
    Iterable<Category> findAllParents(@Param("category") Category category);

    @Query("select ct.ancestor from CategoryTree ct join Category c on ct.descendant.id = c.id where ct.descendant.id = :id")
    Iterable<Category> findAllParents(@Param("id") Long id);

    @Query("select ct.ancestor from CategoryTree ct join Category c on ct.descendant.id = c.id where ct.descendant.id = :id " +
            "and ct.level = (select ct1.level + 1 from CategoryTree ct1 " +
            "where ct1.ancestor = ct1.descendant and ct1.ancestor = ct.ancestor)")
    Category findImmediateParent(@Param("id") Long id);

    Iterable<CategoryTree> findAllByDescendant(Category descendant);

    Iterable<CategoryTree> findAllByDescendant_Id(Long id);

    Iterable<CategoryTree> findAllByAncestor_Id(Long id);

}
