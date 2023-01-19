package com.weberry.backend.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="CHATSPACE")
@Builder @NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString
public class ChatSpace {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	private String spaceName;
	
	@ManyToMany(mappedBy="chatSpaces")
	private List<User> users;
	
	@OneToMany(mappedBy="chatSpace")
	private List<Chat> chats;
	
}
