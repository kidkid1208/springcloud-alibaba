package com.lzh.service.fallbcak;

import com.lzh.domain.Product;
import com.lzh.service.ProductService;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author: lizehui
 * @create: 2020-07-06 17:51
 */
//这是容错类，他要求我们实现一个FallbackFactory<要为哪个接口产生容错类>
@Slf4j
@Component
public class ProductServiceFallbackFactory implements FallbackFactory<ProductService> {

    /**
     * @Param: [throwable] Feign在调用过程中产生的异常
     * @return: com.lzh.service.ProductService
     * @Author: lizehui
     * @Date: 2020/7/6
     */
    @Override
    public ProductService create(Throwable throwable) {
        return new ProductService() {
            @Override
            public Product findByPid(Integer pid) {
                log.error("{}", throwable);
                Product product = new Product();
                product.setPid(-100);
                product.setPname("远程调用商品微服务异常，进入容错逻辑！");
                return product;
            }
        };
    }
}
