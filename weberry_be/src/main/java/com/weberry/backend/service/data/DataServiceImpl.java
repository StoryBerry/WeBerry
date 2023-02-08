package com.weberry.backend.service.data;


import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.cloud.storage.Acl;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
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

	@Autowired
	private Storage storage;
	
	@Value("${storage.url}")
	private String storageUrl;
	
	@Value("${storage.bucket}")
	private String bucket;	
	
	@Override
	public List<Data.ToShow> transferData(List<MultipartFile> imageFiles, DataRequestList request) {
		List<Data.ToShow> dataList = new ArrayList<Data.ToShow>();

		for (int i = 0; i < imageFiles.size(); i++) {
			dataList.add(transferData(imageFiles.get(i), request.getRequestList().get(i)));
		}
		
		return dataList;
	}
	
	private Data.ToShow transferData(MultipartFile imageFile, Request request) {
		String farm = request.getFarm().getFarmId();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yy.MM.dd");
		String toSaveUrl = String.format("farm/%s/%s/%s", request.getMDate().format(format), farm, imageFile.getOriginalFilename());
		String asSavedurl = String.format("%s/%s/farm/%s/%s/%s", storageUrl, bucket, request.getMDate().format(format), farm, imageFile.getOriginalFilename());
		
		BlobInfo blobInfo = null;
		try {
			System.out.println(imageFile.getOriginalFilename());
			blobInfo = storage.create(BlobInfo.newBuilder(bucket, toSaveUrl)
					.setAcl(new ArrayList<Acl>(Arrays.asList(Acl.of(Acl.User.ofAllAuthenticatedUsers(), Acl.Role.READER))))
					.build(), imageFile.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Data toSaveData = Data.Request.toCreate(request);
		dataRepository.save(toSaveData);
		Data savedData = dataRepository.findFirstByMdateAndFarmFarmIdOrderByIdDesc(toSaveData.getMdate(), farm);

		imageRepository.save(Image.Request.toImage(asSavedurl, toSaveData));
		Image savedImage = imageRepository.findByDataId(savedData.getId());
		dataRepository.save(savedImage.setData(savedData));
		
		Data.ToShow saved = Data.ToShow.toShow(dataRepository.findById(toSaveData.getId()).get());
		System.out.println(String.format("Data: %s 와 같이 저장되었습니다. \n", saved));
		
		return saved;
	}

}
