package com.oil.detection.service;

import com.oil.detection.domain.PurchaseSupplierRel;
import com.oil.detection.domain.page.QueryPurchaseSupplierRel;

import java.util.List;

public interface PurchaseSupplierRelService {
    List<PurchaseSupplierRel> listPurchaseSupplierRel(PurchaseSupplierRel purchasesupplierrel);

    int countPurchaseSupplierRel(PurchaseSupplierRel purchasesupplierrel);

    List<PurchaseSupplierRel> pageListPurchaseSupplierRel(QueryPurchaseSupplierRel purchasesupplierrel);

    PurchaseSupplierRel getPurchaseSupplierRel(PurchaseSupplierRel purchasesupplierrel);

    Integer savePurchaseSupplierRel(PurchaseSupplierRel purchasesupplierrel);

    void modifyPurchaseSupplierRel(PurchaseSupplierRel purchasesupplierrel);

    void removePurchaseSupplierRel(PurchaseSupplierRel purchasesupplierrel);
}
