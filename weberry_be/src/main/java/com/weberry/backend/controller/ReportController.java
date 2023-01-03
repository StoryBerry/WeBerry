package com.weberry.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weberry.backend.entity.Report;
import com.weberry.backend.entity.User;
import com.weberry.backend.service.report.ReportService;

@RestController
@RequestMapping("/report")
public class ReportController {
	
	@Autowired
	private ReportService reportService;
	
	@GetMapping()
	public List<Report.ToShow> getDailyReports(@RequestBody User.SignIn user) {
		
		return reportService.getDailyReports(user);
	}
	
}
