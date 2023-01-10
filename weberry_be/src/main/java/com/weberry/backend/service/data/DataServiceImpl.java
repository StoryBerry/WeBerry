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
import com.weberry.backend.entity.DataRequestList;
import com.weberry.backend.entity.Image;
import com.weberry.backend.repository.DataRepository;
import com.weberry.backend.repository.ImageRepository;

@Service
public class DataServiceImpl implements DataService {

	@Autowired
	private DataRepository dataRepository;
	
	@Autowired
	private ImageRepository imageRepository;
	
	@Override
	public List<Data.ToShow> transferData(List<MultipartFile> imageFiles, DataRequestList request) {
		List<Data.ToShow> dataList = new ArrayList<Data.ToShow>();

		for (int i = 0; i < imageFiles.size(); i++) {
			dataList.add(transferData(imageFiles.get(i), request.getRequestList().get(i)));
		}
		
		return dataList;
	}
	
	private Data.ToShow transferData(MultipartFile imageFile, Request request) {
//		String basePath = "/home/weberry/Desktop/weberry_fe/public/images/farm";
		String basePath = "C://users/Will.Lee/desktop/WeBerry/weberry_fe/public/images/farm";
		String farm = request.getFarm().getFarmId();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yy.MM.dd");
		String imageUrl = String.format("%s/%s/%s/%s", basePath, request.getMDate().format(format), farm, imageFile.getOriginalFilename());
		String url = String.format("/images/farm/%s/%s/%s", request.getMDate().format(format), farm, imageFile.getOriginalFilename());
		
		File file = new File(imageUrl);
		file.mkdirs();
		try {
			imageFile.transferTo(file);
			System.out.println(String.format("imageUrl: %s 로 저장합니다.\n", imageUrl));
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		
		Data toSaveData = Data.Request.toCreate(request);
		dataRepository.save(toSaveData);
		Data savedData = dataRepository.findFirstByMdateAndFarmFarmIdOrderByIdDesc(toSaveData.getMdate(), farm);

		imageRepository.save(Image.Request.toImage(url, toSaveData));
		Image savedImage = imageRepository.findByDataId(savedData.getId());
		dataRepository.save(savedImage.setData(savedData));
		
		Data.ToShow saved = Data.ToShow.toShow(dataRepository.findById(toSaveData.getId()).get());
		System.out.println(String.format("Data: %s 와 같이 저장되었습니다. \n", saved));
		
		return saved;
	}

}
