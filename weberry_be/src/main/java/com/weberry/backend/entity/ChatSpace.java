package com.weberry.backend.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="CHATSPACE")
public class ChatSpace {
	
	@Id
	@GeneratedValue
	private long index;
	
	private String spaceName;
	
	@ManyToMany(mappedBy="chatSpaces")
	private List<Profile> profiles;
	
	@OneToMany(mappedBy="chatSpace")
	private List<Chat> chats;
	
}
