package com.weberry.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.weberry.backend.entity.Profile;
import com.weberry.backend.entity.User;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
	
	Profile findByNickNameAndFarmName(String nickName, String farmName);

	Profile findByUser(User user);
	
}
