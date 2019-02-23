package com.azino.project.model.DTO.form;

import com.azino.project.model.Category;
import com.azino.project.model.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormItemWithoutImage {

    @Size(min = Item.NAME_MIN_LENGTH, max = Item.NAME_MAX_LENGTH)
    @NotNull
    private String name;

    @Range(min = Item.PRICE_MIN_VALUE, max = Item.PRICE_MAX_VALUE)
    @NotNull
    private Double price;

    @Range(min = Item.COUNT_IN_STOCK_MIN_VALUE, max = Item.COUNT_IN_STOCK_MAX_VALUE)
    @NotNull
    private Integer countInStock;

    @NotNull
    private Set<Category> categories;

}


