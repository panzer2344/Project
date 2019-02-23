package com.azino.project.model.DTO.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormPurchase {

    @NotNull
    private Long shoppingBasketId;

    /*private String userName;

    //private List<FormItem> items;

    private List<Long> itemsId;

    private Double amount;*/

}
