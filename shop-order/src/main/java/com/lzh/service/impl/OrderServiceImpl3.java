package com.lzh.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.stereotype.Service;

/**
 * @author: lizehui
 * @create: 2020-07-06 10:03
 */
@Service
public class OrderServiceImpl3 {

    int i = 0;

    //定义一个资源
    //定义当资源内部发生异常的时候的处理逻辑
    //blockHandler  定义当资源内部发生了BlockException应该进入的方法[捕获的是Sentinel定义的异常] 优先级高
    //fallback      定义当资源内部发生了Throwable应该进入的方法 全局的 优先级低
    @SentinelResource(
            value = "message",
            blockHandlerClass = OrderServiceImpl3BlockHandler.class,
            blockHandler = "blockHandler",
            fallbackClass = OrderServiceImpl3Fallback.class,
            fallback = "fallback")
    public String message(String name){
        i++;
        if (i % 3 == 0) {
            throw new RuntimeException();
        }
        return "message";
    }
}
