package com.forezp.service;

import com.alibaba.fastjson.JSON;
import com.forezp.config.RabbitConfig;
import com.forezp.config.RabbitMQConstant;
import com.forezp.entity.SysLog;
import com.forezp.util.Send;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by fangzhipeng on 2017/7/12.
 */
@Service
public class LoggerService {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Autowired
    private Send send;

    public void log(SysLog sysLog){
        //rabbitTemplate.convertAndSend(RabbitConfig.queueName, JSON.toJSONString(sysLog));
        send.topicSend(RabbitMQConstant.TOPIC_ROUTINGKEY1,"{\"num\":1}");

    }
}
