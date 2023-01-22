package com.weberry.backend.controller;


import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.weberry.backend.entity.Data;
import com.weberry.backend.entity.DataRequestList;
import com.weberry.backend.entity.ReportRequestList;
import com.weberry.backend.service.data.DataService;
import com.weberry.backend.service.report.ReportService;

@RestController
@RequestMapping(path="/transfer")
public class TransferController {

	@Autowired
	private DataService dataService;
	
	@Autowired
	private ReportService reportService;
	
	@PostMapping
	public ResponseEntity<?> transferData(@RequestPart("imageFiles") List<MultipartFile> imageFiles, @ModelAttribute  DataRequestList request) {
		List<Data.ToShow> savedList = dataService.transferData(imageFiles, request);
		String farmId = savedList.get(0).getFarm().getFarmId();
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders header = new HttpHeaders();
		HttpEntity<?> entity = new HttpEntity<>(header);
		URI uri = UriComponentsBuilder.fromUriString("http://localhost:8000")
									  .path("/analyze/{farmId}")
									  .encode()
									  .build()
									  .expand(farmId)
									  .toUri();
		ResponseEntity<?> resultMap = restTemplate.exchange(uri, HttpMethod.GET, entity, Object.class);
		ObjectMapper mapper = new ObjectMapper();
		Object result = resultMap.getBody();
		System.out.println(result);
		ReportRequestList requestList = mapper.convertValue(result, ReportRequestList.class);
		reportService.writeReport(requestList);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(URI.create(String.format("/alert/daily/report?farmId=%s", farmId)));
		
		return new ResponseEntity<>(headers, HttpStatus.PERMANENT_REDIRECT);
	}
	
}
