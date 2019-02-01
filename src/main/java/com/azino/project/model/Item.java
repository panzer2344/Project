package com.azino.project.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Item implements IModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToOne(targetEntity = Image.class)
    private Image avatar;

    private Double price;

    public Item(String name, Image avatar, Double price) {
        this.name = name;
        this.avatar = avatar;
        this.price = price;
    }

    @ElementCollection(targetClass = Category.class, fetch = FetchType.EAGER)
    @ManyToMany
    private Set<Category> categories;

}
