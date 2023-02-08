package com.weberry.backend.service.user;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.weberry.backend.entity.Farm;
import com.weberry.backend.entity.User;

@Service
public interface UserService {
	
	boolean checkUser(String userId);

	User.SignIn createUser(User.Request request, Farm farm);
	
	Map<String, String> signIn(User user);
	
	String updateUser(User.Request request, Farm farmInfo);
	
	Map<String, Object> checkToken(String token);
}
