package com.lzh.service.impl;

import com.lzh.domain.Order;
import com.lzh.dao.OrderDao;
import com.lzh.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: lizehui
 * @create: 2020-07-03 09:54
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;


    @Override
    public void createOrder(Order order) {
        orderDao.save(order);
    }
}
