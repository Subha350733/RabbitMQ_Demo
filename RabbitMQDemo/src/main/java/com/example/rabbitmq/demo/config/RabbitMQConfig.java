package com.example.rabbitmq.demo.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
	
	// We are using 1 Exchange and multiple queue
	@Value("${rabbitmq.exchange.name}")
	private String exchange ;
	
	// Spring bean for creating RabbitMQ Exchange
	@Bean
	public TopicExchange exchange() {
		return new TopicExchange(exchange);
	}
	
	
	// Exchange will connect with queue using binding key
	
	@Value("${rabbitmq.queue.name}")
	private String queue ;	
	
	@Value("${rabbitmq.binding.key}")
	private String bindingKey ;		
	
		
	
	// Spring bean for creating RabbitMQ queue
	@Bean
	public Queue queue() {
		return new Queue(queue);
	}		
	
	// Spring bean to bind queue with Exchange using binding key	
	@Bean
	public Binding binding() {
		return BindingBuilder
				.bind(queue())
				.to(exchange())
				.with(bindingKey);
	}
	
	// Spring Bean Auto Configuration automatically configure below beans , 
	// Hence we don't have to explicitly configure them	, we just need to inject them when required
	
	// ConnectionFactory
	// RabbitTemplate
	// RabbitAdmin		
	
	
	@Value("${rabbitmq.json.queue.name}")
	private String jsonQueue ;
	
	@Value("${rabbitmq.json.binding.key}")
	private String jsonBindingKey ;
	
	
	// Spring bean for creating RabbitMQ queue
	@Bean
	public Queue jsonQueue() {
		return new Queue(jsonQueue);
	}
	
	// Spring bean to bind queue with Exchange using binding key	
	@Bean
	public Binding jsonBinding() {
		return BindingBuilder
				.bind(jsonQueue())
				.to(exchange())
				.with(jsonBindingKey);
	}
	
	
	// Spring bean for creating RabbitMQ MessageConverter
	@Bean
	public MessageConverter converter() {
		return new Jackson2JsonMessageConverter();
	}
	
	@Bean
	public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(converter());
		return rabbitTemplate ;
	}	
	
	

}
