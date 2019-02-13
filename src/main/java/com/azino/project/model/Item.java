package com.azino.project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item implements IModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToOne(targetEntity = Image.class)
    private Image avatar;

    private Double price;

    private Integer countInStock;

    public Item(String name, Image avatar, Double price) {
        this.name = name;
        this.avatar = avatar;
        this.price = price;
    }

    //@ElementCollection(targetClass = Category.class, fetch = FetchType.EAGER)
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Category> categories;

    public Item(String name, Image avatar, Double price, Set<Category> categories) {
        this.name = name;
        this.avatar = avatar;
        this.price = price;
        this.categories = categories;
    }

    public Item(String name, Image avatar, Double price, Integer countInStock, Set<Category> categories) {
        this.name = name;
        this.avatar = avatar;
        this.price = price;
        this.countInStock = countInStock;
        this.categories = categories;
    }

}
