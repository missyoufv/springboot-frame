package cn.duw.frame;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * 为什么使用消息队列？消息队列有什么优点和缺点？Kafka、ActiveMQ、RabbitMQ、RocketMQ 都有什么优点和缺点？
 *
 */
@SpringBootApplication
@EnableConfigurationProperties
@Slf4j
public class MqApplication {

    public static void main(String[] args) {
        SpringApplication.run(MqApplication.class);
        log.info("springboot-mq start success");
    }
}
