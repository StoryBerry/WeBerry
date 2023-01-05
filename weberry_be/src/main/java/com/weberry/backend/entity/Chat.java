package com.weberry.backend.entity;

import java.util.Date;
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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="CHAT")
@Builder @NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString
public class Chat {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	private String content;
	
	@OneToMany(mappedBy="chat")
	private List<Image> images;
	
	private Date createdAt;
	
	@ManyToOne
	@JoinTable(name="USER_CHAT",
			   joinColumns=@JoinColumn(name="CHAT_INDEX"),
			   inverseJoinColumns=@JoinColumn(name="USERID"))
	private User user;
	
	@ManyToOne
	@JoinTable(name="CHATSPACE_CHAT",
			   joinColumns=@JoinColumn(name="CHAT_INDEX"),
			   inverseJoinColumns=@JoinColumn(name="CHATSPACE_INDEX"))
	private ChatSpace chatSpace;
	
	public User setUser(User user) {
		user.getChats().add(this);
		
		return user;
	}
	
	public ChatSpace setChatSpace(ChatSpace chatSpace) {
		chatSpace.getChats().add(this);
		
		return chatSpace;
	}
	
}
