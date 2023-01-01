package com.weberry.backend.service.report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weberry.backend.entity.Data;
import com.weberry.backend.entity.Report;
import com.weberry.backend.entity.ReportRequestList;
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
	
}
