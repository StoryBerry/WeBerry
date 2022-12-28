package com.weberry.backend.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.weberry.backend.entity.Comment;
import com.weberry.backend.entity.Post;
import com.weberry.backend.service.comment.CommentService;
import com.weberry.backend.service.post.PostService;
import com.weberry.backend.service.user.UserService;

@RestController
@RequestMapping(path="/post")
public class PostController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	PostService postService;
	
	@Autowired
	CommentService commentService;
	
	@GetMapping(path="/check/token")
	public Map<String, Object> checkToken(@RequestHeader(name="Authorization") String token) {
		
		return userService.checkToken(token);
	}
	
	@GetMapping()
	public List<Post.ToShow> getListOfPosts() {
		
		return postService.getListOfPosts();
	}
	
	@PostMapping(path="/write")
	public Post.ToShow writePost(@RequestBody Post.Request request) {
		
		return postService.writePost(request);
	}
	
	@GetMapping(path="/detail")
	public Post.ToShow getDetailOfPost(@RequestParam long postId) {
		
		return postService.getDetailOfPost(postId);
	}
	
	@PostMapping(path="/edit")
	public Post.ToShow editPost(@RequestBody Post.ToEdit toEdit) {
		
		return postService.editPost(toEdit);
	}
	
	@PostMapping(path="/delete/{postId}")
	public String deletePost(@PathVariable("postId") long postId) {
		
		return postService.deletePost(postId);
	}
	
	@PostMapping(path="/comment")
	public Comment.ToShow commentOnPost(@RequestBody Comment.Request request) {
		
		return commentService.commentOnPost(request);
	}
	
	@PostMapping(path="/recomment")
	public Comment.ToShow recommentOnComment(@RequestBody Comment.RecommentRequest request) {
		
		return commentService.recommentOnComment(request);
	}
	
}
