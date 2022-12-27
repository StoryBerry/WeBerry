package com.weberry.backend.service.data;


import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.weberry.backend.entity.Data;
import com.weberry.backend.entity.Data.Request;
import com.weberry.backend.repository.DataRepository;

@Service
public class DataServiceImpl implements DataService {

	@Autowired
	private DataRepository dataRepository;
	
	@Override
	public List<Data> transferData(List<MultipartFile> imageFiles, Request request) {
		List<Data> dataList = new ArrayList<Data>();
		
		for (MultipartFile imageFile : imageFiles) {
			dataList.add(transferData(imageFile, request));
		}
		
		return dataList;
	}
	
	private Data transferData(MultipartFile imageFile, Request request) {
		String basePath = "C://users/playdata/desktop";
		String farm = request.getFarm().getFarmId();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yy.MM.dd");
		String imageUrl = String.format("%s/%s/%s/%s", basePath, request.getMDate().format(format), farm, imageFile.getOriginalFilename());
		
		File file = new File(imageUrl);
		file.mkdirs();
		try {
			System.out.println("imageUrl: " + imageUrl);
			imageFile.transferTo(file);
			System.out.println("after: " + imageUrl);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		
		Data toSave = Data.Request.toCreate(request);
		toSave.setImageUrl(imageUrl);
		dataRepository.save(toSave);
		
		return dataRepository.findFirstBymDateAndFarmFarmIdOrderByIdDesc(request.getMDate(), farm);
	}

}
