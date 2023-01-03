package com.weberry.backend.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.weberry.backend.service.alert.AlertService;

@RestController
@RequestMapping("/alert")
public class AlertController {
	
	@Autowired
	AlertService alterService;
	
	@PostMapping("/daily/report")
	public void alertDailyReport(@RequestParam("farmId") String farmId) {
		try {
			alterService.alterDailyReport(farmId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
