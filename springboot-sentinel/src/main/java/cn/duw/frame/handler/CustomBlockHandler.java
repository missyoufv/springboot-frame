package cn.duw.frame.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;

public class CustomBlockHandler {

    public static String handleException(BlockException exception){
        return "自定义限流信息";
    }

    public static String fallbackMethod(Throwable ex) {
        return "接口触发熔断，熔断原因 "+ ex.getMessage();
    }
}
