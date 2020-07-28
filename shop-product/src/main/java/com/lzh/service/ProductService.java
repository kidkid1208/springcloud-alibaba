package com.lzh.service;

import com.lzh.domain.Product;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {

    //根据pid查询商品信息
    Product findByPid(Integer pid);

    // 减少库存
    void reduceInventory(Integer pid, int num);
}
