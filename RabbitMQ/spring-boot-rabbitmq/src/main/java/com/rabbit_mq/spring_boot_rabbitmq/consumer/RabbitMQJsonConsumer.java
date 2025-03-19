package com.rabbit_mq.spring_boot_rabbitmq.consumer;

import com.rabbit_mq.spring_boot_rabbitmq.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQJsonConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQConsumer.class);
    @RabbitListener(queues = {"${rabbitmq.json.queue.name}"})
    public void consumeJsonMessahe(User user){
        LOGGER.info(String.format("Json message received -> %s", user.toString()));
    }
}
