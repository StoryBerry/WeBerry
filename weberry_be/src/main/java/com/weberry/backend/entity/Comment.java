package com.weberry.backend.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
	
	private LocalDateTime createdAt;
	
	@ManyToOne
	@JoinTable(name="USER_COMMENT",
			   joinColumns=@JoinColumn(name="COMMENT_INDEX"),
			   inverseJoinColumns=@JoinColumn(name="USERID"))
	private User user;
	
	@ManyToOne
	@JoinTable(name="POST_COMMENT",
			   joinColumns=@JoinColumn(name="COMMENT_INDEX"),
			   inverseJoinColumns=@JoinColumn(name="POST_ID"))
	private Post post;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="COMMNENT_INDEX")
	private Comment comment;
	
	@OneToMany(mappedBy="comment")
	private List<Comment> reComments;
	
	@Builder @NoArgsConstructor @AllArgsConstructor
	@Getter @Setter @ToString
	public static class Request {
		
		private String content;
		private LocalDateTime createdAt;
		private User.SignIn user;
		private Post.ToShow post;
		
		public static Comment toCreate(Request request) {
			
			return Comment.builder().content(request.getContent())
									.createdAt(LocalDateTime.now())
									.user(User.CommentIn.toUser(request.getUser()))
									.post(Post.ToShow.toPost(request.getPost()))
									.reComments(new ArrayList<Comment>())
									.build();
		}
		
	}
	
	@Builder @NoArgsConstructor @AllArgsConstructor
	@Getter @Setter @ToString
	public static class RecommentRequest {
		
		private String content;
		private LocalDateTime createdAt;
		private User.SignIn user;
		private Post.ToShow post;
		private Comment.ToShow comment;
		
		public static Comment toCreate(RecommentRequest request) {
			
			return Comment.builder().content(request.getContent())
									.createdAt(LocalDateTime.now())
									.user(User.CommentIn.toUser(request.getUser()))
									.post(Post.ToShow.toPost(request.getPost()))
									.comment(Comment.ToShow.toComment(request.getComment()))
									.build();
		}
		
	}
	
	@Builder @NoArgsConstructor @AllArgsConstructor
	@Getter @Setter @ToString
	public static class ToShow {
		
		private long id;
		private String content;
		private LocalDateTime createdAt;
		private Post.CommentIn post;
		private User.CommentIn user;
		private long commentId;
		private List<Long> reComments;
		
		public static ToShow toShow(Comment comment) {
				
			return ToShow.builder()
					.id(comment.getId())
					.content(comment.getContent())
					.createdAt(comment.getCreatedAt())
					.user(User.CommentIn.toCommentIn(comment.getUser()))
					.post(Post.CommentIn.toCommentIn(comment.getPost()))
					.commentId(getCommentId(comment))
					.reComments(getRecommentIds(comment))
					.build();
		}
		
		public static Comment toComment(Comment.ToShow toShow) {
			
			return Comment.builder()
					 	 .id(toShow.getId())
						 .content(toShow.getContent())
						 .createdAt(toShow.getCreatedAt())
						 .build();
		}
		
		private static long getCommentId(Comment comment) {
			long commentId = 0;
			
			try {
				commentId = comment.getComment().getId();
			} catch (Exception e) {
				commentId = 0;
			}
			
			return commentId;
		}
		
		private static List<Long> getRecommentIds(Comment comment) {
			List<Long> recommentIds = new ArrayList<Long>();
			
			List<Comment> recomments = comment.getReComments();
			if (recomments != null) {
				recomments.stream().forEach(recomment -> recommentIds.add(recomment.getId()));
				
				return recommentIds;
			}
			
			return null;
		}
	}
}
