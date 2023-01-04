package com.weberry.backend.service.report;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weberry.backend.entity.Data;
import com.weberry.backend.entity.Report;
import com.weberry.backend.entity.ReportRequestList;
import com.weberry.backend.entity.User.SignIn;
import com.weberry.backend.repository.DataRepository;
import com.weberry.backend.repository.ReportRepository;

@Service
public class ReportServiceImpl implements ReportService{

	@Autowired
	private ReportRepository reportRepository;

	@Autowired
	private DataRepository dataRepository;
	
	@Override
	public void writeReport(ReportRequestList requestList) {
		
		for (Report.Request request : requestList.getRequestList()) {
			Report.ToShow report = writeReport(request);
			System.out.println(String.format("Report: %s와 같이 저장되었습니다.\n", report));
		}
		
	}
	
	private Report.ToShow writeReport(Report.Request request) {
		Report toSave = Report.Request.toReport(request);
		Data data = dataRepository.findById(toSave.getData().getId()).get();
		toSave.setData(data);
		reportRepository.save(toSave);
		
		return Report.ToShow.toShow(reportRepository.findById(toSave.getId()).get()); 
	}

	@Override
	public List<Report.ToShow> getDailyReports(SignIn user) {
		String farmId = user.getFarm().getFarmId();
		LocalDate today = LocalDate.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyMMdd");
		String id = String.format("%s_%s", farmId, today.format(format));
		
		List<Report.ToShow> toShows = new ArrayList<Report.ToShow>();
		List<Report> reports = reportRepository.findAllByIdStartsWith(id);
		
		try {
			reports.stream().forEach(report -> toShows.add(Report.ToShow.toShow(report)));
			
		} catch (Exception e) {
			System.out.printf("금일 %s에서 측정한 내역이 없습니다.", farmId);
			
		}
		
		return toShows;
	}
	
}
