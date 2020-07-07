package com.lzh.filters;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * 自定义的全局过滤器 作用是统一鉴权
 * 要求是 必须事先GlobalFilter, Ordered接口 并实现里面的两个方法
 * @author: lizehui
 * @create: 2020-07-07 16:09
 */
@Slf4j
//@Component
public class AuthGlobalFilter implements GlobalFilter, Ordered {
    // 过滤器逻辑
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 鉴权统一逻辑
        String token = exchange.getRequest().getQueryParams().getFirst("token");

        if (!StringUtils.equals("admin",token)){
            //认证失败
            log.info("认证失败了。。。");
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        // 放行
        return chain.filter(exchange);
    }


    // 标识当前过滤器优先级，返回值越小，优先级越高
    @Override
    public int getOrder() {
        return 0;
    }
}
