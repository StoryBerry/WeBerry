package com.weberry.backend.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.weberry.backend.entity.Farm;
import com.weberry.backend.entity.User;
import com.weberry.backend.projection.FarmProjection;
import com.weberry.backend.service.farm.FarmService;
import com.weberry.backend.service.user.UserService;

@RestController
@RequestMapping(path="/auth")
public class AuthController {
	
	@Autowired
	private FarmService farmService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping(path="/sign-up/check/user")
	public boolean checkUser(@RequestParam("userId") String userId) {
		System.out.println(String.format("\n checkUser: %s\n", userId));
		
		return userService.checkUser(userId);
	}
	
	@GetMapping(path="/sign-up/check/farm")
	public List<FarmProjection> checkFarm(@RequestParam("farmName") String farmName) {
		System.out.println(String.format("\n checkFarm: %s\n", farmName));

		return farmService.checkFarm(farmName);
	}
	
	@PostMapping(path="/sign-up/create/farm")
	public Farm createFarm(@RequestBody Farm.Request request) {
		System.out.println(String.format("\n createFarm: %s\n", request));

		return farmService.createFarm(request);
	}
	
	@PostMapping(path="/sign-up")
	public User.SignIn signUp(@RequestBody ObjectNode infos) throws JsonProcessingException, IllegalArgumentException {
		System.out.println(String.format("\n signUp\n"));
		ObjectMapper mapper = new ObjectMapper();
		User.Request userInfo = mapper.treeToValue(infos.get("userInfo"), User.Request.class);
		Farm farmInfo = mapper.treeToValue(infos.get("farmInfo"), Farm.class);
		
		return userService.createUser(userInfo, farmInfo);
	}
	
	@PostMapping(path="/sign-in")
	public Map<String, String> signIn(@RequestBody User user) {
		System.out.println(String.format("\n signIn: %s\n", user.getUserid()));
		
		return userService.signIn(user);
	}
		
	
	@PostMapping(path="/update")
	public User.SignIn update(@RequestBody ObjectNode infos) throws JsonProcessingException, IllegalArgumentException {
		ObjectMapper mapper = new ObjectMapper();
		User.Request userInfo = mapper.treeToValue(infos.get("userInfo"), User.Request.class);
		Farm farmInfo = mapper.treeToValue(infos.get("farmInfo"), Farm.class);
		System.out.println(String.format("\n update: %s\n %s\n", userInfo, farmInfo));
		
		return userService.updateUser(userInfo, farmInfo);
	}
	
	@GetMapping(path="/check/token")
	public Map<String, Object> checkToken(@RequestHeader(name="Authorization") String token) {
		System.out.println(String.format("\n checkToken: %s\n", token));
		
		return userService.checkToken(token);
	}
	
}
