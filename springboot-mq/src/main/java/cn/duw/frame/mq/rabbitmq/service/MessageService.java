package cn.duw.frame.mq.rabbitmq.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class MessageService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 消息发送
     */
    public void sendMsg(String exchange, String routeKey, String data) {
        rabbitTemplate.convertAndSend(exchange,routeKey,data);
    }

    public void convertAndSend(String exchange, String routeKey,  Map<String, Object> map) {
        rabbitTemplate.convertAndSend(exchange,routeKey,map);
    }
}
