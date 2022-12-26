package com.weberry.backend.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

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
	
	@GetMapping(path="/sign-up/check")
	public List<FarmProjection> checkFarm(@RequestParam("farmName") String farmName) {
		System.out.println("checkFarm");
		return farmService.checkFarm(farmName);
	}
	
	@PostMapping(path="/sign-up/create/farm")
	public Farm createFarm(@RequestBody Farm.Request request) {

		return farmService.createFarm(request);
	}
	
	@PostMapping(path="/sign-up")
	public ModelAndView signUp(@RequestBody ObjectNode infos, HttpServletRequest request) throws JsonProcessingException, IllegalArgumentException {
		ObjectMapper mapper = new ObjectMapper();
		User.Request userInfo = mapper.treeToValue(infos.get("userInfo"), User.Request.class);
		Farm farmInfo = mapper.treeToValue(infos.get("farmInfo"), Farm.class);
		ModelAndView modelAndView = new ModelAndView("redirect:sign-in");
		modelAndView.addObject("user", userService.createUser(userInfo, farmInfo));
		System.out.println("sign-up: " + modelAndView);
		request.setAttribute(View.RESPONSE_STATUS_ATTRIBUTE, HttpStatus.TEMPORARY_REDIRECT);
		
		return modelAndView;
	}
	
	@PostMapping(path="/sign-in")
	public String signIn(ModelAndView modelAndView) {
		System.out.println("sign-in: " + modelAndView);
		
		return userService.signIn((User) modelAndView.getModel().get("user"));
	}
		
	@GetMapping(path="/check/token")
	public Map<String, Object> checkToken(@RequestHeader(name="Authorization") String token) {
		
		return userService.checkToken(token);
	}
	
}
