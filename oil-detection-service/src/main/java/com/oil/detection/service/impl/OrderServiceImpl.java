package com.oil.detection.service.impl;

import com.oil.detection.dao.OrderMapper;
import com.oil.detection.domain.Order;
import com.oil.detection.domain.page.QueryOrder;
import com.oil.detection.service.OrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;

    @Override
    public List<Order> listOrder(Order order) {
        return orderMapper.listOrder(order);
    }

    @Override
    public int countOrder(Order order) {
        return orderMapper.countOrder(order);
    }

    @Override
    public List<Order> pageListOrder(QueryOrder order) {
        return orderMapper.pageListOrder(order);
    }

    @Override
    public Order getOrder(Order order) {
        return orderMapper.getOrder(order);
    }

    @Override
    public Integer saveOrder(Order order) {
        return orderMapper.saveOrder(order);
    }

    @Override
    public void modifyOrder(Order order) {
        orderMapper.modifyOrder(order);
    }

    @Override
    public void removeOrder(Order order) {
        orderMapper.removeOrder(order);
    }
}
