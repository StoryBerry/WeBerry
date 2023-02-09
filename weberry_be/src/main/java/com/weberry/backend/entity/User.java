package com.weberry.backend.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
	private Farm farm;
	
	@OneToMany(mappedBy="user")
	private List<Post> posts;
	
	@OneToMany(mappedBy="user")
	private List<Comment> comments;
	
	@ManyToMany
	@JoinTable(name="CHATSPACE_USER",
			   joinColumns=@JoinColumn(name="USERID"),
			   inverseJoinColumns=@JoinColumn(name="CHATSPACE_INDEX"))
	private List<ChatSpace> chatSpaces;
	
	@OneToMany(mappedBy="user")
	private List<Chat> chats;
	
	public Farm setFarm(Farm farm) {
		farm.getUsers().add(this);
		
		return farm;
	}
	
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
		
		public static User toUpdate(User user, Request request, Farm farm) {
			
			return User.builder().userid(user.getUserid())
					.password(user.getPassword())
					.name(request.getName())
					.nickName(request.getNickName())
					.farm(farm)
					.posts(user.getPosts())
					.comments(user.getComments())
					.chatSpaces(user.getChatSpaces())
					.chats(user.getChats())
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
		
		public static User toUser(User.SignIn user) {
			
			return User.builder()
					   .userid(user.getUserid())
					   .farm(Farm.SignIn.toFarm(user.getFarm()))
					   .build();
		}
	}
	
	@Builder @NoArgsConstructor @AllArgsConstructor
	@Getter @Setter @ToString
	public static class CommentIn {
		
		private String userid;
		private String name;
		private String nickName;
		
		public static User.CommentIn toCommentIn(User user) {
			
			return User.CommentIn.builder()
							     .userid(user.getUserid())
							     .name(user.getName())
							     .nickName(user.getNickName())
							     .build();
		}
		
		public static User toUser(User.SignIn user) {
			
			return User.builder()
					   .userid(user.getUserid())
					   .name(user.getName())
					   .nickName(user.getNickName())
					   .build();
		}
	}
	
}
