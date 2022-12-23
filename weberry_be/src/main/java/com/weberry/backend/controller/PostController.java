package com.weberry.backend.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.weberry.backend.entity.Post;
import com.weberry.backend.service.post.PostService;
import com.weberry.backend.service.user.UserService;

@RestController
@RequestMapping(path="/post")
public class PostController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	PostService postService;
	
	@GetMapping(path="/check/token")
	public Map<String, Object> checkToken(@RequestHeader(name="Authorization") String token) {
		
		return userService.checkToken(token);
	}
	
	@PostMapping(path="/write")
	public Post writePost(@RequestBody Post.Request request) {
		
		return postService.writePost(request);
	}
	
//	@PostMapping(path="/write")
//	public Post writePost(@RequestBody Post.Request request) {
//		
//		return postService.writePost(request);
//	}
//	
	@GetMapping()
	public List<Post> getListOfPosts() {
		
		return postService.getListOfPosts();
	}
	
	@GetMapping(path="/detail")
	public Post getDetailOfPost(@RequestParam long index) {
		
		return postService.getDetailOfPost(index);
	}
	
}
