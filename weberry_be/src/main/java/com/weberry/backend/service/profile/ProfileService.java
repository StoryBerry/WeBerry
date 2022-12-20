package com.weberry.backend.service.profile;

import org.springframework.stereotype.Service;

import com.weberry.backend.entity.Profile;
import com.weberry.backend.entity.User;

@Service
public interface ProfileService {
	
	Profile createProfile(Profile.Request request);
	
	Profile connectUserAndProfile(User user, Profile profile);
	
	Profile signIn(User user);
	
}
