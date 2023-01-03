package com.weberry.backend.service.report;

import java.util.List;

import org.springframework.stereotype.Service;

import com.weberry.backend.entity.Report;
import com.weberry.backend.entity.ReportRequestList;
import com.weberry.backend.entity.User;

@Service
public interface ReportService {

	void writeReport(ReportRequestList requestList);
	
	List<Report.ToShow> getDailyReports(User.SignIn user);
	
}
