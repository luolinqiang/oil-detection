package com.oil.detection.service.impl;

import com.oil.detection.dao.PurchaseSupplierRelMapper;
import com.oil.detection.domain.PurchaseSupplierRel;
import com.oil.detection.domain.page.QueryPurchaseSupplierRel;
import com.oil.detection.service.PurchaseSupplierRelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PurchaseSupplierRelServiceImpl implements PurchaseSupplierRelService {

    @Resource
    private PurchaseSupplierRelMapper purchasesupplierrelMapper;

    @Override
    public List<PurchaseSupplierRel> listPurchaseSupplierRel(PurchaseSupplierRel purchasesupplierrel) {
        return purchasesupplierrelMapper.listPurchaseSupplierRel(purchasesupplierrel);
    }

    @Override
    public int countPurchaseSupplierRel(PurchaseSupplierRel purchasesupplierrel) {
        return purchasesupplierrelMapper.countPurchaseSupplierRel(purchasesupplierrel);
    }

    @Override
    public List<PurchaseSupplierRel> pageListPurchaseSupplierRel(QueryPurchaseSupplierRel purchasesupplierrel) {
        return purchasesupplierrelMapper.pageListPurchaseSupplierRel(purchasesupplierrel);
    }

    @Override
    public PurchaseSupplierRel getPurchaseSupplierRel(PurchaseSupplierRel purchasesupplierrel) {
        return purchasesupplierrelMapper.getPurchaseSupplierRel(purchasesupplierrel);
    }

    @Override
    public Integer savePurchaseSupplierRel(PurchaseSupplierRel purchasesupplierrel) {
        return purchasesupplierrelMapper.savePurchaseSupplierRel(purchasesupplierrel);
    }

    @Override
    public void modifyPurchaseSupplierRel(PurchaseSupplierRel purchasesupplierrel) {
        purchasesupplierrelMapper.modifyPurchaseSupplierRel(purchasesupplierrel);
    }

    @Override
    public void removePurchaseSupplierRel(PurchaseSupplierRel purchasesupplierrel) {
        purchasesupplierrelMapper.removePurchaseSupplierRel(purchasesupplierrel);
    }
}
