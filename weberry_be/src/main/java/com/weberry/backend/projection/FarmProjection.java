package com.weberry.backend.projection;

import java.util.List;

import com.weberry.backend.entity.Profile;
import com.weberry.backend.entity.User;

public interface FarmProjection {
	
	String getFarmName();
	String getLocal();
	String getCity();
	String getAddress();
	List<UserResponseProjection> getUsers();
	
}
