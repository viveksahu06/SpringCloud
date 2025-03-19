package com.rabbit_mq.spring_boot_rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootRabbitmqApplication {


	public static void main(String[] args) {
		SpringApplication.run(SpringBootRabbitmqApplication.class, args);
		System.out.println("Started");
	}

}
