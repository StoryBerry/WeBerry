package com.weberry.backend.websocket;

import org.springframework.stereotype.Controller;
import org.springframework.web.socket.CloseStatus;
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

}
