package com.lzh.controller;

import com.alibaba.fastjson.JSON;
import com.lzh.domain.Product;
import com.lzh.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: lizehui
 * @create: 2020-07-03 09:52
 */
@RestController
@Slf4j
public class ProductController {

    @Autowired
    ProductService productService;

    @RequestMapping("/product/api1/demo1")
    public String demo1() {
        return "demo";
    }

    @RequestMapping("/product/api1/demo2")
    public String demo2() {
        return "demo";
    }

    @RequestMapping("/product/api2/demo1")
    public String demo3() {
        return "demo";
    }

    @RequestMapping("/product/api2/demo2")
    public String demo4() {
        return "demo";
    }

    //商品信息查询
    @RequestMapping("/product/{pid}")
    public Product product(@PathVariable("pid") Integer pid){
        log.info("接下来进行{}号商品信息查询",pid);
        Product product = productService.findByPid(pid);
        log.info("商品信息查询成功，内容为{}", JSON.toJSONString(product));
        return product;
    }
}
