package com.weberry.backend.service.post;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weberry.backend.entity.Post;
import com.weberry.backend.repository.PostRepository;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	PostRepository postRepository;
	
	@Override
	public List<Post.ToShow> getListOfPosts() {
		List<Post.ToShow> toShowList = new ArrayList<Post.ToShow>();

		List<Post> postList = postRepository.findAllByOrderByIdDesc();
		postList.stream().forEach(post -> toShowList.add(Post.ToShow.toShow(post)));
		
		return toShowList; 
	}
	
	@Override
	public Post.ToShow writePost(Post.Request request) {
		postRepository.save(Post.Request.toWrite(request));
		Post post = postRepository.findFirstByUserUseridOrderByIdDesc(request.getUser().getUserid());
		
		return Post.ToShow.toShow(post); 
	}

	@Override
	public Post.ToShow getDetailOfPost(long id) {
		
		return Post.ToShow.toShow(postRepository.findById(id)); 
	}

	@Override
	public Post.ToShow editPost(Post.ToEdit toEdit) {
		postRepository.save(Post.ToEdit.toEdit(toEdit));
		Post post = postRepository.findById(toEdit.getId());
		
		return Post.ToShow.toShow(post);
	}

	@Override
	public String deletePost(long postId) {
		Post toDelete = postRepository.findById(postId);
		postRepository.delete(toDelete);
		
		return "게시글이 삭제되었습니다.";
	}
	
	

}
