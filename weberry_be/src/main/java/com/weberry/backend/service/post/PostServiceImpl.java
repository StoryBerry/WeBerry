package com.weberry.backend.service.post;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weberry.backend.entity.Post;
import com.weberry.backend.entity.Post.Request;
import com.weberry.backend.repository.PostRepository;
import com.weberry.backend.service.profile.ProfileService;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	PostRepository postRepository;
	
	@Override
	public Post writePost(Post.Request request) {
		postRepository.save(Post.Request.toWrite(request));
		
		return postRepository.findFirstByProfileIndexOrderByIndexDesc(request.getProfile().getIndex());
	}

	@Override
	public List<Post> getListOfPosts() {

		return postRepository.findAllByOrderByIndexDesc();
	}

	@Override
	public Post getDetailOfPost(long index) {
		
		return postRepository.findByIndex(index); 
	}


}
