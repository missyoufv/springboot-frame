package cn.duw.frame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * sentinel 介绍
 *      随着微服务的流行，服务和服务之间的稳定性变得越来越重要。 Sentinel 以流量为切入点，从流量控制、熔断降级、系统负载保护等多个维度保护服务的稳定性。
 *
 * sentinel 控制台介绍
 *      Sentinel 控制台提供一个轻量级的控制台，它提供机器发现、单机资源实时监控、集群资源汇总，以及规则管理的功能。您只需要对应用进行简单的配置，就可以使用这些功能。
 *
 * 启动参数：
 *      java -Dserver.port=8084 指定端口号 -Dcsp.sentinel.dashboard.server=localhost:8084 -jar -sentinel-dashboard.jar 启动控制台
 */
@SpringBootApplication
public class SentinelApplication {

    public static void main(String[] args) {

        SpringApplication.run(SentinelApplication.class);
    }
}
