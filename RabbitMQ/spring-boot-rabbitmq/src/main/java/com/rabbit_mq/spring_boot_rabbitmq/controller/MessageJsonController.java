package com.rabbit_mq.spring_boot_rabbitmq.controller;

import com.rabbit_mq.spring_boot_rabbitmq.dto.User;
import com.rabbit_mq.spring_boot_rabbitmq.publisher.RabbitMQJSONProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class MessageJsonController {
    private RabbitMQJSONProducer rabbitMQJSONProducer;

    public MessageJsonController(RabbitMQJSONProducer rabbitMQJSONProducer) {
        this.rabbitMQJSONProducer = rabbitMQJSONProducer;
    }
    @PostMapping("/publish")
    public ResponseEntity<String> sendJsonMessage(@RequestBody User user){
        rabbitMQJSONProducer.sendJsonMessage(user);
        return ResponseEntity.ok("JsonMessage sent to rabbit mq");
    }
}
