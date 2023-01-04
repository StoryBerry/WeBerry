package com.weberry.backend.service.post;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
	public Post.ToShow writePost(List<MultipartFile> imageFiles, Post.Request request) {
		StringBuilder images = new StringBuilder();
		imageFiles.stream().forEach(imageFile -> images.append(savePostImage(imageFile, request)));
		postRepository.save(Post.Request.toWrite(request, images));
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
	
	private String savePostImage(MultipartFile imageFile, Post.Request request) {
		String basePath = "C://users/playdata/desktop/WeBerry/weberry_fe/public/images/posts";
		String userid = request.getUser().getUserid();
		String imageUrl = String.format("%s/%s/%s", basePath, userid, imageFile.getOriginalFilename());
		String url = String.format("/images/posts/%s/%s, ", userid, imageFile.getOriginalFilename());
		System.out.println(url);
		
		File file = new File(imageUrl);
		file.mkdirs();
		try {
			imageFile.transferTo(file);
			System.out.println(String.format("imageUrl: %s\n", imageUrl));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return url;
	}

}
