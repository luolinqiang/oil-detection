package com.oil.detection.service;

import com.oil.detection.domain.Purchase;
import com.oil.detection.domain.page.QueryPurchase;

import java.util.List;

public interface PurchaseService {
    List<Purchase> listPurchase(Purchase purchase);

    int countPurchase(Purchase purchase);

    List<Purchase> pageListPurchase(QueryPurchase purchase);

    Purchase getPurchase(Purchase purchase);

    Integer savePurchase(Purchase purchase);

    void modifyPurchase(Purchase purchase);

    void removePurchase(Purchase purchase);
}
