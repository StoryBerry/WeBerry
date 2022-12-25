package com.weberry.backend.websocket;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Service;

@Service
@ServerEndpoint(value="/alert", configurator=ServerEndpointConfigurator.class)
public class WebSocketAlert {
	
	private static Set<Session> clients = Collections.synchronizedSet(new HashSet<Session>());
	
	@OnOpen
	public void onOpen(Session session) {
		System.out.println("open Session: " + session.toString());
		
		if (!clients.contains(session)) {
			clients.add(session);
			System.out.println("Session을 연결합니다.");
		}
		else System.out.println("이미 연결된 Session입니다.");
		
	}
	
	@OnClose
	public void onClose(Session session) {
		clients.remove(session);
		System.out.println("close Session: " + session.toString());
	}
	
	@OnMessage
	public void alert(String message) throws IOException {
		System.out.println("received Message: " + message);
		
		for (Session client: clients) {
			System.out.println("send Message to " + client.toString());
			client.getBasicRemote().sendText(message);
		}
	}
	
}
