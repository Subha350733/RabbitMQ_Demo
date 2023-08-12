package com.example.rabbitmq.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.rabbitmq.demo.model.UserDetails;
import com.example.rabbitmq.demo.publisher.RabbitMQJsonProducer;
import com.example.rabbitmq.demo.publisher.RabbitMQProducer;

@RequestMapping("/api/v1")
@RestController
public class MessageController {
	
	private RabbitMQProducer rabbitMQProducer ;
	
	private RabbitMQJsonProducer rabbitMQJsonProducer ;
	
	@Autowired
	public MessageController(RabbitMQProducer rabbitMQProducer , 
								RabbitMQJsonProducer rabbitMQJsonProducer) {
		this.rabbitMQProducer = rabbitMQProducer ;
		this.rabbitMQJsonProducer = rabbitMQJsonProducer ;
	}
	
	// http://localhost:8080/api/v1/publish?message=hello
	
	@GetMapping("/publish")
	public ResponseEntity<String> sendMessage(@RequestParam("message") String message){
		
		rabbitMQProducer.sendMessage(message);
		return new ResponseEntity<String>("Message Sent to RabbitMQ .." , HttpStatus.OK);
	}
	
	
	@PostMapping("/publish")
	public ResponseEntity<String> postMessage(@RequestBody UserDetails user){
		
		rabbitMQJsonProducer.sendMessage(user);
		return new ResponseEntity<String>("Message Sent to RabbitMQ .." , HttpStatus.OK);
	}

}
