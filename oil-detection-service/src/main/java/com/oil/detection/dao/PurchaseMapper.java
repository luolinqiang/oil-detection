package com.oil.detection.dao;

import com.oil.detection.domain.Purchase;
import com.oil.detection.domain.page.QueryPurchase;

import java.util.List;

public interface PurchaseMapper {

    /**
     * 查询列表
     *
     * @param purchase
     * @return
     */
    List<Purchase> listPurchase(Purchase purchase);

    /**
     * 查询总数
     *
     * @param purchase
     * @return
     */
    int countPurchase(Purchase purchase);

    /**
     * 分页列表
     *
     * @param purchase
     * @return
     */
    List<Purchase> pageListPurchase(QueryPurchase purchase);

    /**
     * 查询
     *
     * @param purchase
     * @return
     */
    Purchase getPurchase(Purchase purchase);

    /**
     * 新增
     *
     * @param purchase
     * @author luolinqiang
     */
    Integer savePurchase(Purchase purchase);

    /**
     * 修改
     *
     * @param purchase
     */
    void modifyPurchase(Purchase purchase);

    /**
     * 删除
     *
     * @param purchase
     */
    void removePurchase(Purchase purchase);
}