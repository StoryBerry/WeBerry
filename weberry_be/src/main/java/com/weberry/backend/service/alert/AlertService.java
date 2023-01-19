package com.weberry.backend.service.alert;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

@Service
public interface AlertService {

	void connectToWebSocket(WebSocketSession session, TextMessage message) throws Exception;

	void disconnectToWebSocket(WebSocketSession session) throws Exception;
	
	void alterDailyReport(String farmId) throws Exception;
	
}
