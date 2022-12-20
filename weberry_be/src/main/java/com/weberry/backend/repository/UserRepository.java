package com.weberry.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.weberry.backend.entity.Farm;
import com.weberry.backend.entity.User;
import com.weberry.backend.projection.ProfileBasicResponseProjection;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	User findByUserid(String userid);
	
}
