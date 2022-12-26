package com.weberry.backend.websocket;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.weberry.backend.service.alert.AlertService;
import com.weberry.backend.service.alert.AlertServiceImpl;

@Controller
public class WebSocketAlertHandler extends TextWebSocketHandler {

	AlertService alertService = new AlertServiceImpl();
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		
		alertService.connectToWebSocket(session);
		
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		
		alertService.disconnectToWebSocket(session);
		
	}
	

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
//		System.out.println(session.toString());
//		System.out.println(message.toString());
//		session.sendMessage(new TextMessage("WeBerry에 오신 것을 환영합니다."));
//		for (WebSocketSession client : clients.get("인천_위베리")) client.sendMessage(message);
	}

}
