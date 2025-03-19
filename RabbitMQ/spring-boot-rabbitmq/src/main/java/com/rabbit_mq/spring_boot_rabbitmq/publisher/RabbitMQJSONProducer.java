package com.rabbit_mq.spring_boot_rabbitmq.publisher;

import com.rabbit_mq.spring_boot_rabbitmq.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQJSONProducer {
    @Value("${rabbitmq.exchange.name}")
    private String exchange;
    @Value("${rabbitmq.json.routing.key.name}")
    private String jsonRoutingKey;
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQProducer.class);
    private RabbitTemplate rabbitTemplate;

    public RabbitMQJSONProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendJsonMessage(User user){
        LOGGER.info(String.format("JSON Message sent -> %s", user.toString()));
        rabbitTemplate.convertAndSend(exchange,jsonRoutingKey,user);
    }
}
