package com.azino.project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingBasket implements IModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(targetEntity = User.class)
    private User user;

    @ManyToMany(targetEntity = Item.class, fetch = FetchType.LAZY)
    @JoinTable(
            name = "shopping_basket_items",
            joinColumns = {@JoinColumn(referencedColumnName = "id", name = "shopping_basket_id")},
            inverseJoinColumns = {@JoinColumn(referencedColumnName = "id", name = "items_id")}
    )
    private List<Item> items;

    @Min(0)
    private Double amount;

}
