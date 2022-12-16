package com.weberry.backend.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="CHAT")
public class Chat {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long index;
	
	private String content;
	
//	private ?? images;
	
	private Date createdAt;
	
	@ManyToOne
	@JoinTable(name="PROFILE_CHAT",
			   joinColumns=@JoinColumn(name="CHAT_INDEX"),
			   inverseJoinColumns=@JoinColumn(name="PROFILE_INDEX"))
	private Profile profile;
	
	@ManyToOne
	@JoinTable(name="CHATSPACE_CHAT",
			   joinColumns=@JoinColumn(name="CHAT_INDEX"),
			   inverseJoinColumns=@JoinColumn(name="CHATSPACE_INDEX"))
	private ChatSpace chatSpace;
	
}
