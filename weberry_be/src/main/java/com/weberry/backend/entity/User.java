package com.weberry.backend.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
@Table(name="USERS")
@Builder @NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString
public class User {
	
	@Id
	private String userid;
	
	private String password;
	
	private String name;
	
	private String nickName;
	
	@Column(columnDefinition="boolean default false")
	private boolean admin;
	
	@ManyToOne
	@JoinTable(name="FARM_USER",
			   joinColumns=@JoinColumn(name="USERID"),
			   inverseJoinColumns=@JoinColumn(name="FARMID"))
	@JsonIgnore
	private Farm farm;
	
	@OneToMany(mappedBy="user")
	@JsonIgnore
	private List<Post> posts;
	
	@OneToMany(mappedBy="user")
	@JsonIgnore
	private List<Comment> comments;
	
	@ManyToMany
	@JoinTable(name="CHATSPACE_USER",
			   joinColumns=@JoinColumn(name="USERID"),
			   inverseJoinColumns=@JoinColumn(name="CHATSPACE_INDEX"))
	@JsonIgnore
	private List<ChatSpace> chatSpaces;
	
	@OneToMany(mappedBy="user")
	@JsonIgnore
	private List<Chat> chats;
	
	@Builder @NoArgsConstructor @AllArgsConstructor
	@Getter @Setter @ToString
	public static class Request {

		private String userid;
		private String password;
		private String name;
		private String nickName;
		private Farm farm;
		private List<Post> posts;
		private List<Comment> comments;
		private List<ChatSpace> chatSpaces;
		private List<Chat> chats;
		
		public static User toCreate(Request request, Farm farm) {
			
			return User.builder().userid(request.getUserid())
								 .password(request.getPassword())
								 .name(request.getName())
								 .nickName(request.getNickName())
								 .farm(farm)
								 .posts(new ArrayList<Post>())
								 .comments(new ArrayList<Comment>())
								 .chatSpaces(new ArrayList<ChatSpace>())
								 .chats(new ArrayList<Chat>())
								 .build();
		}
		
	}
	
	@Builder @NoArgsConstructor @AllArgsConstructor
	@Getter @Setter @ToString
	public static class SignIn {
		
		private String userid;
		private String name;
		private String nickName;
		private Farm.SignIn farm;
		
		public static User.SignIn toSignIn(User user) {
			
			return User.SignIn.builder()
					.userid(user.getUserid())
					.name(user.getName())
					.nickName(user.getNickName())
					.farm(Farm.SignIn.toSignIn(user.getFarm()))
					.build();
		}
	}
	
}
