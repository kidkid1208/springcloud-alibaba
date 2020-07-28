package com.lzh.controller;

import com.lzh.domain.Order;
import com.lzh.service.ProductService;
import com.lzh.service.impl.OrderServiceImpl5;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

//@RestController
@Slf4j
public class OrderController5 {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private OrderServiceImpl5 orderService;

    @Autowired
    private ProductService productService;

    //下单--fegin
    @RequestMapping("/order/prod/{pid}")
    public Order order(@PathVariable("pid") Integer pid) {
        log.info("接收到{}号商品的下单请求,接下来调用商品微服务查询此商品信息", pid);
        return orderService.createOrder(pid);
    }


}