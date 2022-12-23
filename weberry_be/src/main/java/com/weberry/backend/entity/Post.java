package com.weberry.backend.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
	private long index;
	
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
		private LocalDateTime createdAt;
		
		public static Post toWrite(Request request) {
			
			return Post.builder().content(request.getContent())
								 .user(request.getUser())
								 .createdAt(LocalDateTime.now())
								 .build();
		}
	}
	
}
