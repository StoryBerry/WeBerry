package com.weberry.backend.service.post;

import java.util.List;

import org.springframework.stereotype.Service;

import com.weberry.backend.entity.Post;

@Service
public interface PostService {

	Post writePost(Post.Request request);
	
	List<Post> getListOfPosts();
	
	Post getDetailOfPost(long index);
	
}
