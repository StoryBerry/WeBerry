package com.weberry.backend.service.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weberry.backend.entity.Comment;
import com.weberry.backend.entity.Comment.Request;
import com.weberry.backend.entity.Comment.ToShow;
import com.weberry.backend.repository.CommentRepository;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	CommentRepository commentRepository;
	
	@Override
	public ToShow commentOnPost(Request request) {
		commentRepository.save(Comment.Request.toCreate(request));
		Comment comment = commentRepository.findFirstByUserUseridOrderByIdDesc(request.getPost().getUser().getUserid());
		System.out.println(comment);
		
		return Comment.ToShow.toShow(comment);
	}

}
