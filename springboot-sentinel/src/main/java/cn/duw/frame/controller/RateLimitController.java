package cn.duw.frame.controller;

import cn.duw.frame.handler.CustomBlockHandler;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 限流
 */
@RestController
public class RateLimitController {
    AtomicInteger atomicInteger = new AtomicInteger(0);


    /**
     * 限流 https://blog.csdn.net/lzb348110175/article/details/107634024
     * @return
     */
    @SentinelResource(value = "limitResource",blockHandler = "handleException",blockHandlerClass= CustomBlockHandler.class)
    @GetMapping(value = "limit" )
    public String limit() {
        atomicInteger.getAndIncrement();
        return "hello sentinel" + atomicInteger.get();
    }



}
