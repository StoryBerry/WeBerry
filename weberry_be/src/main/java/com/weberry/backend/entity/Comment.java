package com.weberry.backend.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="COMMENT")
@Builder @NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString
public class Comment {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	private String content;
	
	private Date createdAt;
	
	@ManyToOne
	@JoinTable(name="USER_COMMENT",
			   joinColumns=@JoinColumn(name="COMMENT_INDEX"),
			   inverseJoinColumns=@JoinColumn(name="USERID"))
	private User user;
	
	@ManyToOne
	@JoinTable(name="POST_COMMENT",
			   joinColumns=@JoinColumn(name="COMMENT_INDEX"),
			   inverseJoinColumns=@JoinColumn(name="POST_INDEX"))
	private Post post;
	
	@ManyToMany
	@JoinTable(name="COMMENT_RECOMMENT",
			   joinColumns=@JoinColumn(name="COMMENT_INDEX"),
			   inverseJoinColumns=@JoinColumn(name="RECOMMENT_INDEX"))
	@JsonIgnore
	private List<Comment> reComments;
	
	@Builder @NoArgsConstructor @AllArgsConstructor
	@Getter @Setter @ToString
	public static class Request {
		
		private String content;
		private Post.ToShow post;
		
		public static Comment toCreate(Request request) {
			
			return Comment.builder().content(request.getContent())
									.user(User.CommentIn.toUser(request.getPost().getUser()))
									.post(Post.ToShow.toPost(request.getPost()))
									.reComments(new ArrayList<Comment>())
									.build();
		}
		
	}
	
	@Builder @NoArgsConstructor @AllArgsConstructor
	@Getter @Setter @ToString
	public static class ToShow {
		
		private long id;
		private String content;
		private Date createdAt;
		private Post.ToShow post;
		private User.CommentIn user;
		private List<Comment> reComments;
		
		public static ToShow toShow(Comment comment) {
			
			return ToShow.builder()
						 .id(comment.getId())
						 .content(comment.getContent())
						 .user(User.CommentIn.toCommentIn(comment.getUser()))
						 .post(Post.ToShow.toShow(comment.getPost()))
						 .reComments(comment.getReComments())
						 .build();
		}
		
	}
}
