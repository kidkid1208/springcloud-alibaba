package com.lzh.service.fallbcak;

import com.lzh.domain.Product;
import com.lzh.service.ProductService;

/**
 * @author: lizehui
 * @create: 2020-07-06 17:14
 */
//这是一个容错类 需要实现Feign所在的所有接口，一旦feign远程调用出现问题
//就会调研同名方法
public class ProductServiceFallback implements ProductService {
    @Override
    public Product findByPid(Integer pid) {
        //容错逻辑
        Product product = new Product();
        product.setPid(-100);
        product.setPname("远程调用商品微服务异常，进入容错逻辑！");
        return product;
    }

    @Override
    public void reduceInventory(Integer pid, int num) {

    }
}
