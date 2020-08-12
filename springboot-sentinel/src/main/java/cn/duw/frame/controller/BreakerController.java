package cn.duw.frame.controller;

import cn.duw.frame.handler.CustomBlockHandler;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 熔断  支持对服务间调用进行保护，对故障应用进行熔断操作
 */
@RestController
public class BreakerController {

    /**
     *  fallback 指定方法执行异常，则调用的降级方法
     *  Sentinel以三种方式衡量被访问的资源是否处理稳定的状态 从而触发熔断
     *      平均响应时间 (RT)
     *          当资源的平均响应时间超过阈值（DegradeRule 中的 count，以 ms 为单位）之后，资源进入准降级状态。接下来如果持续进入 5 个请求，
     *          它们的 RT 都持续超过这个阈值，那么在接下的时间窗口（DegradeRule 中的 timeWindow，以 s 为单位）之内，对这个方法的调用都会自动地返回
     *          （抛出 DegradeException）。在下一个时间窗口到来时, 会接着再放入5个请求, 再重复上面的判断.
     *       异常比例 (DEGRADE_GRADE_EXCEPTION_RATIO)
     *          当资源的每秒异常总数占通过量的比值超过阈值（DegradeRule 中的 count）之后，资源进入降级状态，
     *          即在接下的时间窗口（DegradeRule中的 timeWindow，以 s 为单位）之内，对这个方法的调用都会自动地返回。
     *          异常比率的阈值范围是 [0.0, 1.0]，代表 0% - 100%
     *       异常数 (DEGRADE_GRADE_EXCEPTION_COUNT)
     *          当资源近 1 分钟的异常数目超过阈值之后会进行熔断
     * @return
     */
    @SentinelResource(value = "fallbackResource",fallback = "fallbackMethod",fallbackClass = CustomBlockHandler.class)
    @GetMapping("/fallback")
    public String fallback(){

        int random = new Random().nextInt(100);
        if ((random & 1) !=0 ) {
            throw new RuntimeException("system error,error code is :" + random);
        }
        return "SUCCESS";
    }

    @SentinelResource(value = "timeOutResource",fallback = "fallbackMethod",fallbackClass = CustomBlockHandler.class)
    @GetMapping("/timeOut")
    public String timeOut(){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "SUCCESS";
    }

}
