package com.weberry.backend.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.weberry.backend.entity.Data;


@Repository
public interface DataRepository extends JpaRepository<Data, String>{
	
	Data findFirstBymDateAndFarmFarmIdOrderByIdDesc(LocalDate mDate, String farmId);
	
}
