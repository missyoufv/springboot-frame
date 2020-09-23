package cn.duw.frame.mq.rabbitmq.product;

import cn.duw.frame.mq.rabbitmq.service.MessageService;
import cn.duw.frame.mq.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static cn.duw.frame.mq.rabbitmq.constant.RabbitMqConstant.*;

@RestController
@RequestMapping("mq")
public class MessageProductController {


    @Autowired
    private MessageService messageService;


    @GetMapping("/sendDelayMsg")
    public String sendDelayMsg(String message) {
        //1 创建消息
        messageService.sendMsg(DELAY_EXCHANGE_NAME, DELAY_BINDING_KEY, message);
        return "message send success";
    }

    @GetMapping("/sendMsg")
    public String sendMsg(String message) {
        if (StringUtils.isEmpty(message)) {
            messageService.sendMsg(LOG_LEVEL_EXCHANGE, LOG_LEVEL_ERROR, "消息不能为空!");
        } else if (message.length() < 5) {
            messageService.sendMsg(LOG_LEVEL_EXCHANGE, LOG_LEVEL_IFNO, "消息内容过短!");
        }else {
            messageService.sendMsg(LOG_LEVEL_EXCHANGE, LOG_LEVEL_DEBUG, message);
        }
        return "message send success";
    }

    @GetMapping("/sendMsg2Log")
    public String sendMsg2Log(String message) {
        messageService.sendMsg(LOG_EXCHANGE_NAME,"",message);
        return "message send success";
    }

    /**
     * 测试消息投递确认机制
     * @param message
     * @return
     */
    @GetMapping("/sendMsgAck")
    public String sendMsgAck(String message) {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "message: non-existent-exchange test message ";
        String createTime = DateUtils.formatDateTime();
        Map<String, Object> map = new HashMap<>();
        map.put("messageId", messageId);
        map.put("messageData", messageData);
        map.put("createTime", createTime);
        if (StringUtils.isEmpty(message)) {
            messageService.convertAndSend("not_2_exchange", "TestDirectRouting", map);
        }else
            messageService.convertAndSend(TEST_ACK_EXCHANGE_NAME, "test_ack", map);
        return "message send success";
    }

}
