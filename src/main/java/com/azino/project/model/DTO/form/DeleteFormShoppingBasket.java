package com.azino.project.model.DTO.form;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteFormShoppingBasket {

    @NotNull
    private Long itemId;

    @NotNull
    private Long shoppingBasketId;
}
