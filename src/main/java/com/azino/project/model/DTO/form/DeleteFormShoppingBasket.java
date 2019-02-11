package com.azino.project.model.DTO.form;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteFormShoppingBasket {

    private Long itemId;
    private Long shoppingBasketId;
}
