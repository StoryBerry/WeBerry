package com.weberry.backend.service.user;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.weberry.backend.entity.Farm;
import com.weberry.backend.entity.User;

@Service
public interface UserService {
	
	boolean checkUser(String userId);

	User.SignIn createUser(User.Request request, Farm farm);
	
	void connectUserAndFarm(Farm farmInfo, User user);
	
	String signIn(User user);
	
	Map<String, Object> checkToken(String token);
}
