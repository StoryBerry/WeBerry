package com.weberry.backend.projection;

import com.weberry.backend.entity.Profile;

public interface UserResponseProjection {
	
	String getUserid();
	String getPassword();
	ProfileBasicResponseProjection getProfile();
	
}
