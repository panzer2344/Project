package com.azino.project.service;

import com.azino.project.model.DTO.form.FormPurchase;
import com.azino.project.model.Purchase;

public interface PurchaseService extends BaseService<Purchase> {

    Purchase fromFormPurchase(FormPurchase formPurchase);

    Purchase saveFromFormPurchase(FormPurchase formPurchase);

}
