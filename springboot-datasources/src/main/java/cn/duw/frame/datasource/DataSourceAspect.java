package cn.duw.frame.datasource;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 数据库自动切换切面
 *
 * @author Sunny
 * @version 1.0
 * @className DataSourceAspect
 * @date 2019-08-08 16:44
 */
@Order(-1)
@Component
@Aspect
@Slf4j
public class DataSourceAspect {

    /**
     * 定义方法执行之前的切面信息
     *
     * @param joinPoint
     */
    @Before(value = "@annotation(dataSourceSelector)")
    public void before(JoinPoint joinPoint, DataSourceSelector dataSourceSelector) {
        log.debug("------------------------------------------before dataSourceSelector -----------------------------");
        try {
            DataSourceType dataSourceType = dataSourceSelector.value();
            DataSourceContextHolder.setDbType(dataSourceType);
        } catch (Exception e) {
            log.error("current thread " + Thread.currentThread().getName() + "add data to ThreadLocal error!");
        }
    }

    /**
     * 定义方法执行之后的切面信息
     *
     * @param joinPoint
     */
    @After(value = "@annotation(DataSourceSelector)")
    public void after(JoinPoint joinPoint) {
        log.debug("------------------------------------------after dataSourceSelector -----------------------------");
        DataSourceContextHolder.clearDbType();
    }
}
