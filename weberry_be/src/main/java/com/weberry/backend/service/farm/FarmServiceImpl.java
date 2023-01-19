package com.weberry.backend.service.farm;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weberry.backend.entity.Farm;
import com.weberry.backend.projection.FarmProjection;
import com.weberry.backend.repository.FarmRepository;

@Service
public class FarmServiceImpl implements FarmService {
	
	@Autowired
	private FarmRepository farmRepository;
	
	@Override
	public List<FarmProjection> checkFarm(String farmName) {
		List<FarmProjection> farm = farmRepository.findByFarmName(farmName);

		if (farm.isEmpty()) return null;
		
		return farm;
	}

	@Override
	public Farm createFarm(Farm.Request request) {
		Farm toSave = Farm.Request.toCreate(request);
		farmRepository.save(toSave);
		
		return farmRepository.findByFarmId(toSave.getFarmId());
	}
	
}
