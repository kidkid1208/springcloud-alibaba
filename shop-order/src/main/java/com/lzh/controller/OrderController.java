package com.lzh.controller;

import com.alibaba.fastjson.JSON;
import com.lzh.domain.Order;
import com.lzh.domain.Product;
import lombok.extern.slf4j.Slf4j;
import com.lzh.service.OrderService;
import com.lzh.service.ProductService;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author: lizehui
 * @create: 2020-07-03 09:52
 */
//@RestController
@Slf4j
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
     private RocketMQTemplate rocketMQTemplate;

    //下单
    @RequestMapping("/order/prod/{pid}")
    public Order order(@PathVariable("pid") Integer pid){
        log.info("接收到{}号商品的下单请求,接下来调用商品微服务查询此商品信息", pid);

        //调用商品微服务
        //直接使用微服务名字， 从nacos中获取服务地址
        Product product = productService.findByPid(pid);

        if(product.getPid() == -100){
            Order order = new Order();
            order.setOid(-100L);
            order.setPname("下单失败");
            return order;
        }
        log.info("查询到{}号商品的信息，内容是：{}",pid, JSON.toJSONString(product));

        //下单（创建订单）
        Order order = new Order();
        order.setUid(1);
        order.setPname("测试用户");

        order.setPid(pid);
        order.setPname(product.getPname());
        order.setPprice(product.getPprice());
        order.setNumber(1);

        orderService.createOrder(order);

        log.info("创建订单成功，订单信息为{}", JSON.toJSONString(order));

        //向mq中投递一个下单成功消息
        //参数一 指定topic
        //参数二 指定消息体
        rocketMQTemplate.convertAndSend("order-topic", order);

        return order;
    }
}
