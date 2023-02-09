package com.weberry.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.weberry.backend.entity.Data;
import com.weberry.backend.entity.Report;

@Repository
public interface ReportRepository extends JpaRepository<Report, String>{
	
	Report findByData(Data data);
	
}
