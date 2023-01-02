package com.weberry.backend.service.report;

import org.springframework.stereotype.Service;

import com.weberry.backend.entity.ReportRequestList;

@Service
public interface ReportService {

	void writeReport(ReportRequestList requestList);
	
}
