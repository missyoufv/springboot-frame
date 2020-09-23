package cn.duw.frame.mq.rocketmq.product;

import cn.duw.frame.mq.rocketmq.bean.User;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RequestMapping("/rocketmq")
@RestController
@Slf4j
public class ProducerController {

    @Resource
    private DefaultMQProducer defaultProducer;

    @GetMapping("/sendMsg")
    public String sendMsg() {
        for(int i=0;i<100;i++){
            User user = new User();
            user.setId(String.valueOf(i));
            user.setUsername("hello rocket mq"+i);
            String json = JSON.toJSONString(user);
            Message msg = new Message("user-topic","",json.getBytes());
            try {
                SendResult result = defaultProducer.send(msg);
                System.out.println("消息id:"+result.getMsgId()+":"+","+"发送状态:"+result.getSendStatus());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "message send success";
    }
}
