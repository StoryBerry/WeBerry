package com.weberry.backend.service.post;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.weberry.backend.entity.Post;

@Service
public interface PostService {

	Post.ToShow writePost(List<MultipartFile> imageFiles, Post.Request request);
	
	List<Post.ToShow> getListOfPosts();
	
	Post.ToShow getDetailOfPost(long id);
	
	Post.ToShow editPost(Post.ToEdit toEdit);
	
	String deletePost(long postId);
}
