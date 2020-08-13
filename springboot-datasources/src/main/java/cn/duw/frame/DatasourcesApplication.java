package cn.duw.frame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


/**
 * 多数据源切换
 */
@SpringBootApplication
@EnableAspectJAutoProxy( exposeProxy = true, proxyTargetClass = true)
public class DatasourcesApplication {

    public static void main(String[] args) {

        SpringApplication.run(DatasourcesApplication.class);
    }
}
