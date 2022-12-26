package com.weberry.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.weberry.backend.entity.Report;

@Repository
public interface ReportRepository extends JpaRepository<Report, String>{

}