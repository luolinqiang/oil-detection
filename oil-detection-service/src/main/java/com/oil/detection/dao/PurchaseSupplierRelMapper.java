package com.oil.detection.dao;

import com.oil.detection.domain.PurchaseSupplierRel;
import com.oil.detection.domain.page.QueryPurchaseSupplierRel;

import java.util.List;

public interface PurchaseSupplierRelMapper {

    /**
     * 查询列表
     *
     * @param purchasesupplierrel
     * @return
     */
    List<PurchaseSupplierRel> listPurchaseSupplierRel(PurchaseSupplierRel purchasesupplierrel);

    /**
     * 查询总数
     *
     * @param purchasesupplierrel
     * @return
     */
    int countPurchaseSupplierRel(PurchaseSupplierRel purchasesupplierrel);

    /**
     * 分页列表
     *
     * @param purchasesupplierrel
     * @return
     */
    List<PurchaseSupplierRel> pageListPurchaseSupplierRel(QueryPurchaseSupplierRel purchasesupplierrel);

    /**
     * 查询
     *
     * @param purchasesupplierrel
     * @return
     */
    PurchaseSupplierRel getPurchaseSupplierRel(PurchaseSupplierRel purchasesupplierrel);

    /**
     * 新增
     *
     * @param purchasesupplierrel
     * @author luolinqiang
     */
    Integer savePurchaseSupplierRel(PurchaseSupplierRel purchasesupplierrel);

    /**
     * 修改
     *
     * @param purchasesupplierrel
     */
    void modifyPurchaseSupplierRel(PurchaseSupplierRel purchasesupplierrel);

    /**
     * 删除
     *
     * @param purchasesupplierrel
     */
    void removePurchaseSupplierRel(PurchaseSupplierRel purchasesupplierrel);
}