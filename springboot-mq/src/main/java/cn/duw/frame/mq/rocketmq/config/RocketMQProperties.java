package cn.duw.frame.mq.rocketmq.config;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.ArrayList;
import java.util.List;

/**
 * 读取rocketMq信息
 *
 * @PropertySource:指定要加载的配置文件路径,其实默认是可以不写的,如果是加载classpath下面的配置文件,因为它会自己去寻找,但是有时候不同的版本,不写的话又会出现错误,所以为了不出现错误,通常建议配置一下,这样肯定不会有错的
 * @ConfigurationProperties:指定读取配置文件的规则,比如前缀是什么,不存在的字段是否可以忽略等
 * @Configuration:相当于xml的配置标签<beans>
 */
@Configuration
@PropertySource(value = "classpath:config/rocketmq.properties")
@Data
@Accessors(chain = true)
@ConfigurationProperties(prefix = "rocketmq")
public class RocketMQProperties {

    private String namesrvAddr;
    private String producerGroupName;
    private String transactionProducerGroupName;
    private String consumerGroupName;
    private String producerInstanceName;
    private String consumerInstanceName;
    private String producerTranInstanceName;
    private int consumerBatchMaxSize;
    private boolean consumerBroadcasting;
    private boolean enableHistoryConsumer;
    private boolean enableOrderConsumer;
    private List<String> subscribe = new ArrayList<String>();


}
