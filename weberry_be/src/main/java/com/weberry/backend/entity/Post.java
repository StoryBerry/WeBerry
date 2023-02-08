package com.weberry.backend.entity;

import java.time.LocalDateTime;
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
// team
public class Post {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	private String content;
	
	
	private LocalDateTime createdAt;
	
	private LocalDateTime modifiedAt;
	
	@ColumnDefault("0")
	private long likes;
	
	@OneToMany(mappedBy="post")
	private List<Image> images;
	
	@ManyToOne
	@JoinTable(name="USER_POST",
			   joinColumns=@JoinColumn(name="POST_INDEX"),
			   inverseJoinColumns=@JoinColumn(name="USERID"))
	private User user;
	
	@OneToMany(mappedBy="post")
	private List<Comment> comments;
	
	public User setUser(User user) {
		user.getPosts().add(this);
		
		return user; 
	}
	
	@Builder @NoArgsConstructor @AllArgsConstructor
	@Getter @Setter @ToString
	public static class Request {
		
		private String content;
		private String userid;
		
		public static Post toWrite(Request request, User user) {

			return Post.builder().content(request.getContent())
								 .user(user)
								 .images(new ArrayList<Image>())
								 .createdAt(LocalDateTime.now())
								 .build();
		}
	}
	
	@Builder @NoArgsConstructor @AllArgsConstructor
	@Getter @Setter @ToString
	public static class ToShow {
		
		private long id;
		private String content;
		private List<Image.ToShow> images;
		private User.SignIn user;
		private List<Comment.ToShow> comments;
		private LocalDateTime createdAt;
		private LocalDateTime modifiedAt; 
		
		public static ToShow toShow(Post post, User user) {
			List<Comment.ToShow> commentList = new ArrayList<Comment.ToShow>();
			List<Comment> comments = post.getComments();
			if (comments != null) comments.stream().forEach(comment -> commentList.add(Comment.ToShow.toShow(comment)));
			
			List<Image.ToShow> imageList = new ArrayList<Image.ToShow>();
			List<Image> images = post.getImages();
			if (images != null) images.stream().forEach(image -> imageList.add(Image.ToShow.toShow(image)));
			
			return ToShow.builder().id(post.getId())
								   .content(post.getContent())
								   .createdAt(post.getCreatedAt())
								   .modifiedAt(post.getModifiedAt())
								   .images(imageList)
								   .user(User.SignIn.toSignIn(user))
								   .comments(commentList)
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
		private List<Image> images;
		private User user;
		private LocalDateTime createdAt;
		private LocalDateTime modifiedAt;
		
		public static Post toEdit(ToEdit toEdit) {
			
			return Post.builder().id(toEdit.getId())
								 .content(toEdit.getContent())
								 .images(toEdit.getImages())
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
		private List<Image.ToShow> images;
		private LocalDateTime createdAt;
		private LocalDateTime modifiedAt;
		
		public static CommentIn toCommentIn(Post post) {
			List<Image.ToShow> imageList = new ArrayList<Image.ToShow>();
			List<Image> images = post.getImages();
			if (images != null) images.stream().forEach(image -> imageList.add(Image.ToShow.toShow(image)));
			
			return CommentIn.builder().id(post.getId())
									  .content(post.getContent())
									  .images(imageList)
									  .createdAt(post.getCreatedAt())
									  .modifiedAt(post.getModifiedAt())
									  .build();
		}
	}
	
	
}
