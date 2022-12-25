package com.weberry.backend.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.weberry.backend.service.alert.AlertService;

@RestController
@RequestMapping("/alert")
public class AlertController {
	
	@Autowired
	AlertService alterService;
	
	@PostMapping("/daily/report")
	public void alertDailyReport(@RequestParam("farmId") String farmId) {
		try {
			alterService.alterDailyReport(farmId);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
