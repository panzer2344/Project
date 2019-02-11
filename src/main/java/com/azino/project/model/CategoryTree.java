package com.azino.project.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class CategoryTree implements IModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(targetEntity = Category.class)
    private Category ancestor;

    @OneToOne(targetEntity = Category.class)
    private Category descendant;

    private Integer level;
}
