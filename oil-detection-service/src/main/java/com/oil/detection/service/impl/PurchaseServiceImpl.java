package com.oil.detection.service.impl;

import com.oil.detection.dao.PurchaseMapper;
import com.oil.detection.domain.Purchase;
import com.oil.detection.domain.page.QueryPurchase;
import com.oil.detection.service.PurchaseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    @Resource
    private PurchaseMapper purchaseMapper;

    @Override
    public List<Purchase> listPurchase(Purchase purchase) {
        return purchaseMapper.listPurchase(purchase);
    }

    @Override
    public int countPurchase(Purchase purchase) {
        return purchaseMapper.countPurchase(purchase);
    }

    @Override
    public List<Purchase> pageListPurchase(QueryPurchase purchase) {
        return purchaseMapper.pageListPurchase(purchase);
    }

    @Override
    public Purchase getPurchase(Purchase purchase) {
        return purchaseMapper.getPurchase(purchase);
    }

    @Override
    public Integer savePurchase(Purchase purchase) {
        return purchaseMapper.savePurchase(purchase);
    }

    @Override
    public void modifyPurchase(Purchase purchase) {
        purchaseMapper.modifyPurchase(purchase);
    }

    @Override
    public void removePurchase(Purchase purchase) {
        purchaseMapper.removePurchase(purchase);
    }
}
