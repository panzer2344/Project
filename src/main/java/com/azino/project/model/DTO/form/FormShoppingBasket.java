package com.azino.project.model.DTO.form;

import com.azino.project.model.Item;
import com.azino.project.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FormShoppingBasket {

    @NotNull
    private Long id;

    private User user;

    private List<Item> items;

}
