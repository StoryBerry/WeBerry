package com.weberry.backend.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.weberry.backend.entity.Data;


@Repository
public interface DataRepository extends JpaRepository<Data, String>{
	
	Data findFirstBymDateAndFarmFarmIdOrderByIdDesc(LocalDate mDate, String farmId);
	
}
