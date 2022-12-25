package com.weberry.backend.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class AlertController {

	@Autowired
	SimpMessagingTemplate simpMessagingTemplate;
	
	@MessageMapping("/application")
	@SendTo("/all/messages")
	public <T> Message<T> send(final Message<T> message) {
		
		return message;
	}
	
	@MessageMapping("/private")
	public void sendToSpecificUser(@Payload Message message) {
	}
	
}
