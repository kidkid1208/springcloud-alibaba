package com.lzh.service;

import com.lzh.domain.Product;
import com.lzh.service.fallbcak.ProductServiceFallback;
import com.lzh.service.fallbcak.ProductServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(
        value = "service-product",
        //fallback = ProductServiceFallback.class,
        fallbackFactory = ProductServiceFallbackFactory.class
)
public interface ProductService {

    @RequestMapping("/product/{pid}")
    Product findByPid(@PathVariable("pid") Integer pid);
}
