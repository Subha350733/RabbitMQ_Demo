package com.example.rabbitmq.demo.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.rabbitmq.demo.model.UserDetails;

@Service
public class RabbitMQJsonProducer {
	
	public static final Logger logger = LoggerFactory.getLogger(RabbitMQJsonProducer.class);
	
	@Value("${rabbitmq.exchange.name}")
	private String exchange ;
	
	@Value("${rabbitmq.json.binding.key}")
	private String jsonBindingKey ;
	
	
	// Spring Auto Configuration will automatically create "RabbitTemplate" object
	// Hence we are just injecting it to use it
	private RabbitTemplate rabbitTemplate ;
	
	
	@Autowired
	public RabbitMQJsonProducer(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate ;
	}
	
	
	public void sendMessage(UserDetails user) {
		logger.info(String.format("Message sent -> %s" , user.toString()));
		rabbitTemplate.convertAndSend(exchange, jsonBindingKey, user);
	}

}
