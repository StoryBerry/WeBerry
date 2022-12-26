package com.weberry.backend.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.weberry.backend.entity.Data;
import com.weberry.backend.service.data.DataService;

@RestController
@RequestMapping(path="/transfer")
public class TransferController {

	@Autowired
	private DataService dataService;
	
	@PostMapping
	public Data transferData(@ModelAttribute("imageFile") MultipartFile imageFile, @ModelAttribute Data.Request request) {
		System.out.println(request.getFarm());
		System.out.println(request.getMDate());
		
		return dataService.transferData(imageFile, request);
	}
	
}