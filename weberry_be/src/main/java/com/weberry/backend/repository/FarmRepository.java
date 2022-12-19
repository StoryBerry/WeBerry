package com.weberry.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.weberry.backend.entity.Farm;
import com.weberry.backend.projection.FarmProjection;

@Repository
public interface FarmRepository extends JpaRepository<Farm, Long>{
	List<FarmProjection> findByFarmName(String farmName);
	Farm findByFarmNameAndAddress(String farmName, String address);
}
