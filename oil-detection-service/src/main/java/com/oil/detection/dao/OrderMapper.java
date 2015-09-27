package com.oil.detection.dao;

import com.oil.detection.domain.Order;
import com.oil.detection.domain.page.QueryOrder;

import java.util.List;

public interface OrderMapper {

    /**
     * 查询列表
     *
     * @param order
     * @return
     */
    List<Order> listOrder(Order order);

    /**
     * 查询总数
     *
     * @param order
     * @return
     */
    int countOrder(Order order);

    /**
     * 分页列表
     *
     * @param order
     * @return
     */
    List<Order> pageListOrder(QueryOrder order);

    /**
     * 查询
     *
     * @param order
     * @return
     */
    Order getOrder(Order order);

    /**
     * 新增
     *
     * @param order
     * @author luolinqiang
     */
    Integer saveOrder(Order order);

    /**
     * 修改
     *
     * @param order
     */
    void modifyOrder(Order order);

    /**
     * 删除
     *
     * @param order
     */
    void removeOrder(Order order);
}