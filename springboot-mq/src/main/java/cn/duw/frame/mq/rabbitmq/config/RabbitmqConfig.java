package cn.duw.frame.mq.rabbitmq.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

import static cn.duw.frame.mq.rabbitmq.constant.RabbitMqConstant.*;

/**
 * rabbitmq配置
 */
@Configuration
@Slf4j
public class RabbitmqConfig {

    /**
     *
     *
     * 如何保证消息发送成功？
     *      事务和publisher Confirm机制
     *
     *      publisher Confirm机制（发送者确认）
     *          发送确认分为两步，一是确认是否到达交换器，二是确认是否到达队列。
     *          对于ConfirmCallback来说：
     *              如果消息没有到exchange,则confirm回调,ack=false
     *              如果消息到达exchange,则confirm回调,ack=true
     *
     *          对于ReturnCallback来说：
     *              exchange到queue成功,则不回调return
     *              exchange到queue失败,则回调return(需设置mandatory=true,否则不回回调,消息就丢了)
     *
     * 先从总体的情况分析，推送消息存在四种情况：
     *
     * ①消息推送到server，但是在server里找不到交换机
     *      这种情况触发的是 ConfirmCallback 回调函数。
     * ②消息推送到server，找到交换机了，但是没找到队列
     *      到这种情况，两个函数都被调用了。送成功到服务器了的，所以ConfirmCallback对消息确认情况是true；
     *      而在RetrunCallback回调函数的打印参数里面可以看到，消息是推送到了交换机成功了，但是在路由分发给队列的时候，找不到队列，所以报了错误 NO_ROUTE 。
     * ③消息推送到sever，交换机和队列啥都没找到
     *       这种情况触发的是 ConfirmCallback 回调函数。
     * ④消息推送成功
     *       种情况触发的是 ConfirmCallback 回调函数。
     *
     * @param connectionFactory
     * @return
     */
    @Bean
    public RabbitTemplate createRabbitTemplate(ConnectionFactory connectionFactory){

        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        //设置开启Mandatory,才能触发回调函数,无论消息推送结果怎么样都强制调用回调函数
        rabbitTemplate.setMandatory(true);

        // 如果消息没有到exchange,则confirm回调,ack=false; 如果消息到达exchange,则confirm回调,ack=true
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if(ack){
                log.info("消息发送成功:correlationData({}),ack({}),cause({})",correlationData,ack,cause);
            }else{
                log.info("消息发送失败:correlationData({}),ack({}),cause({})",correlationData,ack,cause);
            }
        });

        //如果exchange到queue成功,则不回调return;如果exchange到queue失败,则回调return(需设置mandatory=true,否则不回回调,消息就丢了)
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
            log.info("消息丢失:exchange({}),route({}),replyCode({}),replyText({}),message:{}",exchange,routingKey,replyCode,replyText,message);
        });

        return rabbitTemplate;
    }


    /**
     * 延迟队列绑定的交换机
     * @return
     */
    @Bean
    public DirectExchange delayExchange() {
        return new DirectExchange(DELAY_EXCHANGE_NAME);
    }
    /**
     * 延迟队列
     * @return
     */
    @Bean
    public Queue delayQueue() {
        Map<String, Object> map = new HashMap<>(16);
        //指定死信送往的交换机
        map.put("x-dead-letter-exchange", WORK_EXCHANGE_NAME);
        //指定死信的routingkey
        map.put("x-dead-letter-routing-key", WORK_RECEIVE_KEY);
        map.put("x-message-ttl", 60000);
        return new Queue(DELAY_QUEUE_NAME,true,false,false,map);
    }
    @Bean
    public Binding delayQueueBinding() {
        return BindingBuilder.bind(delayQueue()).to(delayExchange()).with(DELAY_BINDING_KEY);
    }
    /**
     * 工作交换机
     * @return
     */
    @Bean
    public TopicExchange workExchange() {
        return new TopicExchange(WORK_EXCHANGE_NAME);
    }
    /**
     * 工作队列
     * @return
     */
    @Bean
    public Queue workQueue() {
        return new Queue(WORK_QUEUE_NAME);
    }


    @Bean
    public Binding workBinding() {
        return BindingBuilder.bind(workQueue()).to(workExchange()).with(WORK_RECEIVE_KEY);
    }

    @Bean
    public Queue debugQueue() {
        return new Queue(DEBUG_QUEUE_NAME);
    }

    @Bean
    public Queue infoQueue() {
        return new Queue(INFO_QUEUE_NAME);
    }

    @Bean
    public Queue errorQueue() {
        return new Queue(ERROR_QUEUE_NAME);
    }

    @Bean
    public TopicExchange logLevelExchange() {
        return new TopicExchange(LOG_LEVEL_EXCHANGE);
    }

    @Bean
    public Binding debugLogLevelBinding() {
        return BindingBuilder.bind(debugQueue()).to(logLevelExchange()).with(LOG_LEVEL_DEBUG);
    }

    @Bean
    public Binding infoLogLevelBinding() {
        return BindingBuilder.bind(infoQueue()).to(logLevelExchange()).with(LOG_LEVEL_IFNO);
    }

    @Bean
    public Binding errorLogLevelBinding() {
        return BindingBuilder.bind(errorQueue()).to(logLevelExchange()).with(LOG_LEVEL_ERROR);
    }


    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(LOG_EXCHANGE_NAME);
    }

    @Bean
    public Binding debugLogBinding() {
        return BindingBuilder.bind(debugQueue()).to(fanoutExchange());
    }

    @Bean
    Binding infoLogBinding() {
        return BindingBuilder.bind(infoQueue()).to(fanoutExchange());
    }

    @Bean
    Binding errorLogBinding() {
        return BindingBuilder.bind(errorQueue()).to(fanoutExchange());
    }


    @Bean
    public DirectExchange testAckExchange() {
        return new DirectExchange(TEST_ACK_EXCHANGE_NAME);
    }

    @Bean
    public Queue testAckQueue() {
        return new Queue(TEST_ACK_QUEUE_NAME);
    }

    @Bean
    public Binding testAckBinding() {
        return BindingBuilder.bind(testAckQueue()).to(testAckExchange()).with("test_ack");
    }
}
