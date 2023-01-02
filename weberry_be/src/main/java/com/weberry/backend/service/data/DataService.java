package com.weberry.backend.service.data;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.weberry.backend.entity.Data;
import com.weberry.backend.entity.DataRequestList;

@Service
public interface DataService {

	List<Data.ToShow> transferData(List<MultipartFile> imageFiles, DataRequestList request);
	
}
