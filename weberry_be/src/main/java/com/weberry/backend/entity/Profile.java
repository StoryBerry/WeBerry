package com.weberry.backend.entity;

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

@Entity
@Table(name="PROFILE")
public class Profile {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long index;
	
	private String name;
	
	private String nickName;
	
	private String farmName;
	
	@OneToOne
	@JoinTable(name="USER_PROFILE",
			   joinColumns=@JoinColumn(name="PROFILE_INDEX"),
			   inverseJoinColumns=@JoinColumn(name="USER_INDEX"))
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
	
}
