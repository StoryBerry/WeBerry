package com.weberry.backend.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="PROFILE")
@Builder @NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString
public class Profile {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long index;
	
	private String name;
	
	private String nickName;
	
	private String farmName;
	
	@OneToOne
	@JoinTable(name="USER_PROFILE",
			   joinColumns=@JoinColumn(name="PROFILE_INDEX"),
			   inverseJoinColumns=@JoinColumn(name="USER_INDEX"))
	@JsonIgnore
	private User user;
	
	@OneToMany(mappedBy="profile")
	private List<Post> posts;
	
	@OneToMany(mappedBy="profile")
	private List<Comment> comments;
	
	@ManyToMany
	@JoinTable(name="CHATSPACE_PROFILE",
			   joinColumns=@JoinColumn(name="PROFILE_INDEX"),
			   inverseJoinColumns=@JoinColumn(name="CHATSPACE_INDEX"))
	private List<ChatSpace> chatSpaces;
	
	@OneToMany(mappedBy="profile")
	private List<Chat> chats;
	
	@Builder @NoArgsConstructor @AllArgsConstructor
	@Getter @Setter @ToString
	public static class Request {
		
		private String name;
		private String nickName;
		private String farmName;
		private List<Post> posts;
		private List<Comment> comments;
		private List<ChatSpace> chatSpaces;
		private List<Chat> chats;
		
		public static Profile toCreate(Request request) {
			
			return Profile.builder().name(request.getName())
									.nickName(request.getNickName())
									.farmName(request.getFarmName())
									.posts(new ArrayList<Post>())
									.comments(new ArrayList<Comment>())
									.chatSpaces(new ArrayList<ChatSpace>())
									.chats(new ArrayList<Chat>()).build();
		}
		
	}
	
	@Builder @AllArgsConstructor
	@Getter @Setter @ToString
	public static class BasicResponse {
		
		private String name;
		private String nickName;
		private String farmName;
		
	}
	
}
