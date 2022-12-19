package com.weberry.backend.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weberry.backend.entity.Farm;
import com.weberry.backend.entity.Profile;
import com.weberry.backend.entity.User;
import com.weberry.backend.entity.User.Request;
import com.weberry.backend.repository.FarmRepository;
import com.weberry.backend.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private FarmRepository farmRepository;
	
	@Override
	public User createUser(Request request) {
		userRepository.save(User.Request.toCreate(request));
		
		return userRepository.findByUserid(request.getUserid());
	}
	
	public void connectUserAndFarm(Farm farmInfo, User user) {
		Farm toSave = farmRepository.findByFarmNameAndAddress(farmInfo.getFarmName(), farmInfo.getAddress());
		user.setFarm(toSave);
	}
	
}
