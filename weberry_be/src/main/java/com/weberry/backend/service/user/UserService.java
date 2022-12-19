package com.weberry.backend.service.user;

import org.springframework.stereotype.Service;

import com.weberry.backend.entity.Farm;
import com.weberry.backend.entity.Profile;
import com.weberry.backend.entity.User;

@Service
public interface UserService {

	User createUser(User.Request request);
	
	void connectUserAndFarm(Farm farmInfo, User user);
	
}
