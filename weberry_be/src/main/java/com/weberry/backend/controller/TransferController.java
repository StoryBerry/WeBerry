package com.weberry.backend.controller;


import java.util.List;

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
	public List<Data> transferData(@ModelAttribute("imageFile") List<MultipartFile> imageFiles, @ModelAttribute Data.Request request) {
		System.out.println(request.getFarm());
		System.out.println(request.getMDate());
		System.out.println(imageFiles.size() + ": " + imageFiles);
		
		return dataService.transferData(imageFiles, request);
	}
	
}
