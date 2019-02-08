package com.azino.project.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
//@Data
//@Getter
//@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category implements IModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    private Category parent;

    //@ElementCollection(targetClass = Category.class)
    @OneToMany
    private List<Category> childs;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(id, category.id) &&
                Objects.equals(name, category.name) &&
                Objects.equals(parent, category.parent) &&
                Objects.equals(childs, category.childs);
    }

    @Override
    public int hashCode() {
        if (null == parent) {
            if (null == childs)
                Objects.hash(id, name);
            else
                Objects.hash(id, name, childs);
        } else if (null == childs) {
            Objects.hash(id, name, parent);
        }
        return Objects.hash(id, name, parent, childs);
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ((null != parent) ? (", parent=" + parent) : "") +
                ((null != childs) ? (", childs=" + childs) : "") +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    public List<Category> getChilds() {
        return childs;
    }

    public void setChilds(List<Category> childs) {
        this.childs = childs;
    }
}
