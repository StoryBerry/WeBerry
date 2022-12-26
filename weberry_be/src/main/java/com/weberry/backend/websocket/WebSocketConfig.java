package com.weberry.backend.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(new WebSocketAlertHandler(), "/ws");
		registry.addHandler(new WebSocketAlertHandler(), "/ws").withSockJS();
	}
	
//	@Bean
//	public WebSocketHandler createHandler() {
//		
//		return new AlertController1();
//	}
//	
//	@Override
//	public void configureMessageBroker(MessageBrokerRegistry registry) {
//		registry.enableSimpleBroker("/all", "/specific");
//		registry.setApplicationDestinationPrefixes("/app");
//	}
	
}
