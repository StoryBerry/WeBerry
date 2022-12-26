package com.weberry.backend.service.alert;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.weberry.backend.entity.User;
import com.weberry.backend.service.user.UserService;
import com.weberry.backend.service.user.UserServiceImpl;

@Service
public class AlertServiceImpl implements AlertService {
	
	@Autowired
	UserService userService = new UserServiceImpl();
	
	private static Map<String, Set<WebSocketSession>> clientsOnFarm = new HashMap<String, Set<WebSocketSession>>();
	private static Map<String, String> sessionAndFarmId = new HashMap<String, String>();
	
	@Override
	public void connectToWebSocket(WebSocketSession session) throws Exception {
		String token = session.getHandshakeHeaders().get("Authorization").get(0);
		String farmId = checkAuthorization(token);
		
		if (farmId != null) {
			sessionAndFarmId.put(session.getId(), farmId);
			clientsOnFarm.put(farmId, new HashSet<WebSocketSession>());
			clientsOnFarm.get(farmId).add(session);
			session.sendMessage(new TextMessage("WeBerry에 오신 것을 환영합니다."));
			System.out.println("Session을 연결합니다: " + session);
		} else {
			session.sendMessage(new TextMessage("토큰이 만료되었습니다. 다시 로그인해주세요."));
		}
	}

	@Override
	public void disconnectToWebSocket(WebSocketSession session) throws Exception {
		String sessionId = session.getId();
		String farmId = sessionAndFarmId.get(sessionId);
		sessionAndFarmId.remove(sessionId);
		clientsOnFarm.get(farmId).remove(session);
		
		System.out.printf("Session을 종료합니다: <%s> %s\n", farmId, session);
	}

	@Override
	public void alterDailyReport(String farmId) throws Exception {
		Set<WebSocketSession> clients = clientsOnFarm.get(farmId);
		for (WebSocketSession client : clients) {
			client.sendMessage(new TextMessage("데일리 레포트가 작성되었습니다."));
		}
	}

	private String checkAuthorization(String token) {
		String farmId = null;
		Map<String, Object> tokenInfo = userService.checkToken(token);
		
		if (tokenInfo.containsKey("error")) return null;
		
		try {
			ObjectMapper mapper = new ObjectMapper();
			farmId = mapper.readValue((String) tokenInfo.get("signIn"), User.SignIn.class).getFarm().getFarmId();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
			
			return null;
		}
		
		return farmId;
	}

}
