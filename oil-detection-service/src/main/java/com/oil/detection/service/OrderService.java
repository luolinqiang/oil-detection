package com.oil.detection.service;

import com.oil.detection.domain.Order;
import com.oil.detection.domain.page.QueryOrder;

import java.util.List;

public interface OrderService {
    List<Order> listOrder(Order order);

    int countOrder(Order order);

    List<Order> pageListOrder(QueryOrder order);

    Order getOrder(Order order);

    Integer saveOrder(Order order);

    void modifyOrder(Order order);

    void removeOrder(Order order);
}
