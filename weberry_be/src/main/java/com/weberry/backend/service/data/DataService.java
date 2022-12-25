package com.weberry.backend.service.data;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.weberry.backend.entity.Data;

@Service
public interface DataService {

	Data transferData(MultipartFile imageFile, Data.Request request);
	
}
