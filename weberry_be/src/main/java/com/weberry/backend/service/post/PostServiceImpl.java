package com.weberry.backend.service.post;

import java.io.File;
import java.io.IOException;
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
import com.weberry.backend.entity.Image;
import com.weberry.backend.entity.Post;
import com.weberry.backend.entity.User;
import com.weberry.backend.repository.ImageRepository;
import com.weberry.backend.repository.PostRepository;
import com.weberry.backend.repository.UserRepository;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	PostRepository postRepository;
	
	@Autowired
	ImageRepository imageRepository;
	
	@Autowired
	UserRepository userRepository;

	@Autowired
	private Storage storage;
	
	@Value("${storage.url}")
	private String storageUrl;
	
	@Value("${storage.bucket}")
	private String bucket;
	
	@Override
	public List<Post.ToShow> getListOfPosts() {
		List<Post.ToShow> toShowList = new ArrayList<Post.ToShow>();

		List<Post> postList = postRepository.findAllByOrderByIdDesc();
		for (Post post : postList) {
			User user = userRepository.findById(post.getUser().getUserid()).get();
			toShowList.add(Post.ToShow.toShow(post, user));
		}
		
		return toShowList; 
	}
	
	@Override
	public Post.ToShow writePost(List<MultipartFile> imageFiles, Post.Request request) {
		User user = userRepository.findById(request.getUserid()).get();
		postRepository.save(Post.Request.toWrite(request, user));
		Post saved = postRepository.findFirstByUserUseridOrderByIdDesc(request.getUserid());
		imageFiles.stream().forEach(imageFile -> savePostImage(imageFile, saved));
		
		return Post.ToShow.toShow(postRepository.findById(saved.getId()), user);
	}

	@Override
	public Post.ToShow getDetailOfPost(long id) {
		Post post = postRepository.findById(id);
		
		return Post.ToShow.toShow(post, post.getUser()); 
	}

	@Override
	public Post.ToShow editPost(Post.ToEdit toEdit) {
		postRepository.save(Post.ToEdit.toEdit(toEdit));
		Post post = postRepository.findById(toEdit.getId());
		
		return Post.ToShow.toShow(post, post.getUser());
	}

	@Override
	public String deletePost(long postId) {
		Post toDelete = postRepository.findById(postId);
		postRepository.delete(toDelete);
		
		return "게시글이 삭제되었습니다.";
	}
	
	private void savePostImage(MultipartFile imageFile, Post post) {
		String userid = post.getUser().getUserid();
		String toSaveUrl = String.format("posts/%s/%s", userid, imageFile.getOriginalFilename());
		String asSavedurl = String.format("%s/%s/posts/%s/%s", storageUrl, bucket, userid, imageFile.getOriginalFilename());
		
		BlobInfo blobInfo = null;
		try {
			System.out.println(imageFile.getOriginalFilename());
			blobInfo = storage.create(BlobInfo.newBuilder(bucket, toSaveUrl)
					.setAcl(new ArrayList<Acl>(Arrays.asList(Acl.of(Acl.User.ofAllAuthenticatedUsers(), Acl.Role.READER))))
					.build(), imageFile.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Post savedPost = postRepository.findById(post.getId());
		imageRepository.save(Image.Request.toImage(asSavedurl, savedPost));
		Image savedImage = imageRepository.findById(asSavedurl).get();
		postRepository.save(savedImage.setPost(savedPost));

	}

}
