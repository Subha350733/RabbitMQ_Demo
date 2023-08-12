package com.example.rabbitmq.demo.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.example.rabbitmq.demo.model.UserDetails;

@Service
public class RabbitMQConsumer {
	
	private static final Logger logger = LoggerFactory.getLogger(RabbitMQConsumer.class);
	
	//@Value("${rabbitmq.queue.name}")
	//private static String queue ;
	
	@RabbitListener(queues = {"${rabbitmq.queue.name}"})
	public void consumeMessage(String message) {
		logger.info(String.format("Message received -> %s", message));
	}
	
	@RabbitListener(queues = {"${rabbitmq.json.queue.name}"})
	public void consumeJsonMessage(UserDetails user) {
		logger.info(String.format("Message received -> %s", user.getFirstName()));
	}

}
