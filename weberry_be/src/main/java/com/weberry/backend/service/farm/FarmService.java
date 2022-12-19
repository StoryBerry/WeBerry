package com.weberry.backend.service.farm;

import java.util.List;

import org.springframework.stereotype.Service;

import com.weberry.backend.entity.Farm;
import com.weberry.backend.entity.User;
import com.weberry.backend.projection.FarmProjection;

@Service
public interface FarmService {
	
	List<FarmProjection> checkFarm(String farmName);
	
	Farm createFarm(Farm.Request farm);
}
