package com.weberry.backend.service.user;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weberry.backend.entity.Farm;
import com.weberry.backend.entity.User;
import com.weberry.backend.entity.User.Request;
import com.weberry.backend.repository.FarmRepository;
import com.weberry.backend.repository.UserRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class UserServiceImpl implements UserService {
	
	private final String key = "WEBERRY";
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private FarmRepository farmRepository;
	
	@Override
	public boolean checkUser(String userId) {
		try {
			User user = userRepository.findById(userId).get();
		} catch (Exception e) {
			return true;
		}
		return false;
	}
	
	@Override
	public User.SignIn createUser(Request request, Farm farmInfo) {
		Farm farm = farmRepository.findByFarmId(String.format("%s_%s", farmInfo.getCity(), farmInfo.getFarmName()));
		userRepository.save(User.Request.toCreate(request, farm));
		User saved = userRepository.findById(request.getUserid()).get();
		Farm savedFarm = farmRepository.findByFarmId(farm.getFarmId());
		farmRepository.save(saved.setFarm(savedFarm));
		
		return User.SignIn.toSignIn(saved);
	}

	@Override
	public Map<String, String> signIn(User user) {
		User toCheck = userRepository.findById(user.getUserid()).get();
		
		if (toCheck.getPassword().equals(user.getPassword())) {
			User.SignIn signIn = User.SignIn.toSignIn(toCheck);
			Map<String, String> map = new HashMap<String, String>();
			map.put("token", createToken(signIn));
			return map;
		}
		
		return null;
	}
	
	@Override
	public Map<String, Object> checkToken(String token) {
		
		return verifyToken(token);
	}

	private String createToken(User.SignIn signIn) {
		
		Map<String, Object> headers = new HashMap<String, Object>();
		headers.put("typ", "JWT");
		headers.put("alg", "HS256");
		
		Map<String, Object> payloads = new HashMap<String, Object>();
		JSONObject jsonObject = new JSONObject(signIn);
		payloads.put("signIn", jsonObject.toString());
		
		long expirationTime = 1000 * 60 * 60 * 24 * 1l; // 하루
		
		Date expirationDate = new Date();
		expirationDate.setTime(expirationDate.getTime() + expirationTime);
		
		String token = Jwts.builder().setHeader(headers)
									 .setClaims(payloads)
									 .setSubject("user")
									 .setExpiration(expirationDate)
									 .signWith(SignatureAlgorithm.HS256, key)
									 .compact();
		
		return token;
	}
	
	private Map<String, Object> verifyToken(String token) {
		
		Map<String, Object> claimMap = new HashMap<String, Object>();
		
		try {
			
			Claims claims = Jwts.parser()
								.setSigningKey(key)
								.parseClaimsJws(token)
								.getBody();
			claimMap = claims;
			
		} catch (ExpiredJwtException e) {
			claimMap.put("error", "Expired Token");
			System.out.println("Expired Token");
			System.out.println(e);
		} catch (Exception e) {
			claimMap.put("error", "No Token");
			System.out.println("No Token");
			System.out.println(e);
		}
		
		return claimMap;
	}
}
