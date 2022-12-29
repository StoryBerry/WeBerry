package com.weberry.backend.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.weberry.backend.entity.Data;
import com.weberry.backend.entity.DataRequestList;
import com.weberry.backend.service.data.DataService;

@RestController
@RequestMapping(path="/transfer")
public class TransferController {

	@Autowired
	private DataService dataService;
	
	@PostMapping
	public List<Data> transferData(@RequestPart("imageFile") List<MultipartFile> imageFiles, @ModelAttribute  DataRequestList request) {
		System.out.println(String.format("request: %s\n", request));
		System.out.println(String.format("requestList: %s\n", request.getRequestList()));
		System.out.println(String.format("length: %d\n", request.getRequestList().size()));
		System.out.println(String.format("1: %s", request.getRequestList().get(0)));
		
		return dataService.transferData(imageFiles, request);
	}
	
}
