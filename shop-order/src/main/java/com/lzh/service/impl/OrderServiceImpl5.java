package com.lzh.service.impl;

import com.alibaba.fastjson.JSON;
import com.lzh.dao.OrderDao;
import com.lzh.domain.Order;
import com.lzh.domain.Product;
import com.lzh.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: lizehui
 * @create: 2020-07-03 09:54
 */
@Service
@Slf4j
public class OrderServiceImpl5 {
    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ProductService productService;

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    //@GlobalTransactional//全局事务控制
    public Order createOrder(Integer pid) {
        log.info("接收到{}号商品的下单请求,接下来调用商品微服务查询此商品信息", pid);

        //调用商品微服务
        //直接使用微服务名字， 从nacos中获取服务地址
        Product product = productService.findByPid(pid);

        log.info("查询到{}号商品的信息，内容是：{}",pid, JSON.toJSONString(product));

        //下单（创建订单）
        Order order = new Order();
        order.setUid(1);
        order.setPname("测试用户");

        order.setPid(pid);
        order.setPname(product.getPname());
        order.setPprice(product.getPprice());
        order.setNumber(1);

        orderDao.save(order);

        log.info("创建订单成功，订单信息为{}", JSON.toJSONString(order));

        // 扣库存
        productService.reduceInventory(pid, order.getNumber());

        //向mq中投递一个下单成功消息
        //参数一 指定topic
        //参数二 指定消息体
        rocketMQTemplate.convertAndSend("order-topic", order);

        return order;
    }
}
