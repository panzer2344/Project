package com.azino.project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item implements IModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(min = NAME_MIN_LENGTH, max = NAME_MAX_LENGTH)
    private String name;

    @OneToOne(targetEntity = Image.class)
    @NotNull
    private Image avatar;

    @Min(PRICE_MIN_VALUE)
    @Max(PRICE_MAX_VALUE)
    private Double price;

    @Min(COUNT_IN_STOCK_MIN_VALUE)
    @Max(COUNT_IN_STOCK_MAX_VALUE)
    private Integer countInStock;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Category> categories;

    @Transient
    public static final int PRICE_MAX_VALUE = Integer.MAX_VALUE;

    @Transient
    public static final int PRICE_MIN_VALUE = 0;

    @Transient
    public static final int COUNT_IN_STOCK_MAX_VALUE = 100000;

    @Transient
    public static final int COUNT_IN_STOCK_MIN_VALUE = 0;

    @Transient
    public static final int NAME_MIN_LENGTH = 1;

    @Transient
    public static final int NAME_MAX_LENGTH = 30;

    public Item(String name, Image avatar, Double price) {
        this.name = name;
        this.avatar = avatar;
        this.price = price;
    }

    //@ElementCollection(targetClass = Category.class, fetch = FetchType.EAGER)


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
