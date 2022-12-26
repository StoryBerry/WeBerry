package com.weberry.backend.websocket;

import javax.websocket.server.ServerEndpointConfig.Configurator;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServerEndpointConfigurator extends Configurator implements ApplicationContextAware {

	private static volatile BeanFactory context;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		ServerEndpointConfigurator.context = applicationContext;
	}

	@Override
	public <T> T getEndpointInstance(Class<T> clazz) throws InstantiationException {
		return context.getBean(clazz);
	}
	
}