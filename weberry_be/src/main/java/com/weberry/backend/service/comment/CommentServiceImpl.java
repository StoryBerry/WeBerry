package com.weberry.backend.service.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weberry.backend.entity.Comment;
import com.weberry.backend.entity.Comment.RecommentRequest;
import com.weberry.backend.entity.Comment.Request;
import com.weberry.backend.entity.Comment.ToShow;
import com.weberry.backend.entity.Post;
import com.weberry.backend.entity.User;
import com.weberry.backend.repository.CommentRepository;
import com.weberry.backend.repository.PostRepository;
import com.weberry.backend.repository.UserRepository;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	CommentRepository commentRepository;
	
	@Autowired
	PostRepository postRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public ToShow commentOnPost(Request request) {
		User user = userRepository.findById(request.getUserid()).get();
		Post post = postRepository.findById(request.getPostid());
		commentRepository.save(Comment.Request.toCreate(request, user, post));
		Comment savedComment = commentRepository.findFirstByUserUseridOrderByIdDesc(request.getUserid());
		userRepository.save(savedComment.setUser(user));
		postRepository.save(savedComment.setPost(post));
		
		return Comment.ToShow.toShow(savedComment);
	}

	@Override
	public ToShow recommentOnComment(RecommentRequest request) {
		System.out.println(String.format("%s, %d, %d", request.getUserid(), request.getPostid(), request.getCommentid()));
		User user = userRepository.findById(request.getUserid()).get();
		Post post = postRepository.findById(request.getPostid());
		Comment comment = commentRepository.findById(request.getCommentid()).get();
		commentRepository.save(Comment.RecommentRequest.toCreate(request, user, post, comment));
		Comment savedRecomment = commentRepository.findFirstByUserUseridOrderByIdDesc(request.getUserid());
		commentRepository.save(savedRecomment.setComment(comment));
		
		return Comment.ToShow.toShow(savedRecomment);
	}

}
