package com.weberry.backend.projection;

import java.util.List;

public interface FarmProjection {
	
	String getFarmId();
	String getFarmName();
	String getLocal();
	String getCity();
	String getAddress();
	List<UserBasicProjection> getUsers();
	
	interface UserBasicProjection {
		
		String getUserid();
		String getName();
		String getNickName();
		
	}
	
}
