package com.weberry.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@SpringBootApplication
public class WeberryBeApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeberryBeApplication.class, args);
	}
	
//	@Bean
//	public ServerEndpointExporter serverEndpointExporter() {
//		
//		return new ServerEndpointExporter();
//	}

}
