package com.azino.project.service;

import com.azino.project.model.DTO.form.FormPurchase;
import com.azino.project.model.Item;
import com.azino.project.model.Purchase;

import java.util.List;

public interface PurchaseService extends BaseService<Purchase> {

    Purchase fromFormPurchase(FormPurchase formPurchase);

    Purchase saveFromFormPurchase(FormPurchase formPurchase);

    List<Purchase> findAllPurchasesByItem(Item item);

    List<Purchase> findAllPurchasesByItemId(Long id);

}
