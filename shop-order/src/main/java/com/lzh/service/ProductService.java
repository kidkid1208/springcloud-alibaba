package com.lzh.service;

import com.lzh.domain.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "service-product")//调用的微服务名称
public interface ProductService {

    @RequestMapping("/product/{pid}")
    Product findByPid(@PathVariable("pid") Integer pid);
}
