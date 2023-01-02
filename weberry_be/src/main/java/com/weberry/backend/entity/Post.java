package com.weberry.backend.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="POST")
@Builder @NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString
public class Post {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	private String content;
	
//	private ?? images;
	
	private LocalDateTime createdAt;
	
	private LocalDateTime modifiedAt;
	
	@ColumnDefault("0")
	private long likes;
	
	@ManyToOne
	@JoinTable(name="USER_POST",
			   joinColumns=@JoinColumn(name="POST_INDEX"),
			   inverseJoinColumns=@JoinColumn(name="USERID"))
	private User user;
	
	@OneToMany(mappedBy="post")
	private List<Comment> comments;
	
	@Builder @NoArgsConstructor @AllArgsConstructor
	@Getter @Setter @ToString
	public static class Request {
		
		private String content;
//		private ?? images;
		private User user;
		
		public static Post toWrite(Request request) {
			
			return Post.builder().content(request.getContent())
								 .user(request.getUser())
								 .createdAt(LocalDateTime.now())
								 .build();
		}
	}
	
	@Builder @NoArgsConstructor @AllArgsConstructor
	@Getter @Setter @ToString
	public static class ToShow {
		
		private long id;
		private String content;
//		private ?? images;
		private User.SignIn user;
		private List<Comment.ToShow> comments;
		private LocalDateTime createdAt;
		private LocalDateTime modifiedAt; 
		
		public static ToShow toShow(Post post) {
			List<Comment.ToShow> toShowList = new ArrayList<Comment.ToShow>();
			
			List<Comment> commentList = post.getComments();
			if (commentList != null) commentList.stream().forEach(comment -> toShowList.add(Comment.ToShow.toShow(comment)));
			
			return ToShow.builder().id(post.getId())
								   .content(post.getContent())
								   .createdAt(post.getCreatedAt())
								   .modifiedAt(post.getModifiedAt())
								   .user(User.SignIn.toSignIn(post.getUser()))
								   .comments(toShowList)
								   .build();
		}
		
		public static Post toPost(Post.ToShow post) {
			
			return Post.builder().id(post.getId())
								 .content(post.getContent())
								 .user(User.SignIn.toUser(post.getUser()))
								 .createdAt(post.getCreatedAt())
								 .modifiedAt(post.getModifiedAt())
								 .build();
		}
	}
	
	@Builder @NoArgsConstructor @AllArgsConstructor
	@Getter @Setter @ToString
	public static class ToEdit {

		private long id;
		private String content;
//		private ?? images;
		private User user;
		private LocalDateTime createdAt;
		private LocalDateTime modifiedAt;
		
		public static Post toEdit(ToEdit toEdit) {
			
			return Post.builder().id(toEdit.getId())
								 .content(toEdit.getContent())
								 .user(toEdit.getUser())
								 .createdAt(toEdit.getCreatedAt())
								 .modifiedAt(LocalDateTime.now())
								 .build();
		}
	}
	
	@Builder @NoArgsConstructor @AllArgsConstructor
	@Getter @Setter @ToString
	public static class CommentIn {
		
		private long id;
		private String content;
//		private ?? images;
		private LocalDateTime createdAt;
		private LocalDateTime modifiedAt;
		
		public static CommentIn toCommentIn(Post post) {
			
			return CommentIn.builder().id(post.getId())
									  .content(post.getContent())
									  .createdAt(post.getCreatedAt())
									  .modifiedAt(post.getModifiedAt())
									  .build();
		}
	}
	
	
}
