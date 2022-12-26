package com.weberry.backend.service.data;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.weberry.backend.entity.Data;

@Service
public interface DataService {

	List<Data> transferData(List<MultipartFile> imageFiles, Data.Request request);
	
}
