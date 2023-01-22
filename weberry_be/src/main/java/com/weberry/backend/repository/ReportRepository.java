package com.weberry.backend.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.weberry.backend.entity.Data;
import com.weberry.backend.entity.Report;

@Repository
public interface ReportRepository extends JpaRepository<Report, String>{
	
	Report findByData(Data data);
	
}
