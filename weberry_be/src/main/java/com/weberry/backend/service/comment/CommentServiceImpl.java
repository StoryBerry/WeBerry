package com.weberry.backend.service.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weberry.backend.entity.Comment;
import com.weberry.backend.entity.Comment.Request;
import com.weberry.backend.entity.Comment.ToShow;
import com.weberry.backend.entity.Post;
import com.weberry.backend.repository.CommentRepository;
import com.weberry.backend.repository.PostRepository;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	CommentRepository commentRepository;
	
	@Autowired
	PostRepository postRepository;
	
	@Override
	public ToShow commentOnPost(Request request) {
		commentRepository.save(Comment.Request.toCreate(request));
		Comment comment = commentRepository.findFirstByUserUseridOrderByIdDesc(request.getPost().getUser().getUserid());
		System.out.println(comment);
		System.out.println(comment.getUser());
		System.out.println(Post.ToShow.toShow(postRepository.findById(comment.getPost().getId())));
		
		return Comment.ToShow.toShow(comment);
	}

}
