package cn.duw.frame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


/**
 * 多数据源切换
 *     大多数项目只需要一个事务管理器。如果存在多数据源的情况，事务管理器是否会生效，由于spingboot约定大于配置的理念，
 *    默认事务管理器无需我们再声明定义，而是默认加载时已经指定了其数据源，其数据源则为缺省数据源，如果执行事务时是第二数据源，则
 *    还会以第一数据源做处理，这时则会异常。
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableAspectJAutoProxy( exposeProxy = true, proxyTargetClass = true)
public class DatasourcesApplication {

    public static void main(String[] args) {

        SpringApplication.run(DatasourcesApplication.class);
    }
}
