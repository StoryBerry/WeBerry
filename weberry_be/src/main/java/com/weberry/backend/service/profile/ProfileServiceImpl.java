package com.weberry.backend.service.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weberry.backend.entity.Profile;
import com.weberry.backend.entity.Profile.Request;
import com.weberry.backend.entity.User;
import com.weberry.backend.repository.ProfileRepository;

@Service
public class ProfileServiceImpl implements ProfileService {
	
	@Autowired
	private ProfileRepository profileRepository;

	@Override
	public Profile createProfile(Request request) {
		profileRepository.save(Profile.Request.toCreate(request));
		
		return profileRepository.findByNickNameAndFarmName(request.getNickName(), request.getFarmName());
	}
	
	@Override
	public Profile connectUserAndProfile(User user, Profile profile) {
		profile.setUser(user);
		profileRepository.save(profile);
		
		return profile;
	}

	@Override
	public Profile signIn(User user) {
		
		return profileRepository.findByUser(user);
	}
	
}
