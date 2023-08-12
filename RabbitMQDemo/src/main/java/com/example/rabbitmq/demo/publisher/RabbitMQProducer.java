package com.example.rabbitmq.demo.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQProducer {
	
	public static final Logger logger = LoggerFactory.getLogger(RabbitMQProducer.class);
	
	@Value("${rabbitmq.exchange.name}")
	private String exchange ;
	
	@Value("${rabbitmq.binding.key}")
	private String bindingKey ;
	
	
	// Spring Auto Configuration will automatically create "RabbitTemplate" object
	// Hence we are just injecting it to use it
	private RabbitTemplate rabbitTemplate ;
	
	@Autowired
	public RabbitMQProducer(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate ;
	}
	
	public void sendMessage(String message) {
		logger.info(String.format("Message sent -> %s" , message));
		rabbitTemplate.convertAndSend(exchange, bindingKey, message);
	}

}
