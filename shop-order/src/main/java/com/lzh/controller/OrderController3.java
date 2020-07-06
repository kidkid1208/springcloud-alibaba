package com.lzh.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import lombok.extern.slf4j.Slf4j;
import com.lzh.service.impl.OrderServiceImpl3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: lizehui
 * @create: 2020-07-03 09:52
 */
@RestController
@Slf4j
public class OrderController3 {

    @Autowired
    private    OrderServiceImpl3 orderServiceImpl3;

    int i = 0;
    @RequestMapping("/order/message1")
    public String message1() {
//        i++;
//        //异常比例为0.333
//        if (i % 3 == 0) {
//            throw new RuntimeException();
//        }
        return "message1";
    }

    @RequestMapping("/order/message2")
    public String message2() {
        //orderServiceImpl3.message();
        return "message2";
    }

    @RequestMapping("/order/message3")
    @SentinelResource("message3")
    public String message3(String name, Integer age) {
        return "message3"+name+age;
    }

    @RequestMapping("/order/message")
    public String message() {
        return orderServiceImpl3.message("xx");
    }
}