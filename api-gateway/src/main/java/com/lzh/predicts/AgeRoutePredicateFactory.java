package com.lzh.predicts;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

/**
 * 自定义的路由断言工厂类
 * 要求：
 * 名字：配置+RoutePredicateFactory
 * 继承路由断言工厂
 * @author: lizehui
 * @create: 2020-07-07 15:10
 */
//@Component
public class AgeRoutePredicateFactory extends AbstractRoutePredicateFactory<AgeRoutePredicateFactory.Config> {
    public static final String DATETIME_KEY = "datetime";

    public AgeRoutePredicateFactory() {
        super(AgeRoutePredicateFactory.Config.class);
    }

    //读取配置文件中的参数值 赋值到配置类Config的属性上
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("minAge","maxAge");
    }

    public Predicate<ServerWebExchange> apply(AgeRoutePredicateFactory.Config config) {
        return new Predicate<ServerWebExchange>() {
            @Override
            public boolean test(ServerWebExchange serverWebExchange) {
                // 接收前台传入的age参数
                String ageStr = serverWebExchange.getRequest().getQueryParams().getFirst("age");
                // 先判断是否为空
                if(StringUtils.isNotEmpty(ageStr)){
                    int age = Integer.parseInt(ageStr);
                    // 如果不为空进行路由逻辑判断
                    if(age < config.getMaxAge() && age > config.getMinAge()){
                        return true;
                    }
                }
                return false;
            }
        };
    }

    /**
     *
     * 用于接收配置文件中对应参数
     * @return:
     * @Author: lizehui
     * @Date: 2020/7/7
     */
    @Data
    @NoArgsConstructor
    public static class Config {
       private int minAge;
       private int maxAge;
    }
}
