package com.weberry.backend.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="COMMENT")
public class Comment {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long index;
	
	private String content;
	
	private Date createdAt;
	
	@ManyToOne
	@JoinTable(name="PROFILE_COMMENT",
			   joinColumns=@JoinColumn(name="COMMENT_INDEX"),
			   inverseJoinColumns=@JoinColumn(name="PROFILE_INDEX"))
	private Profile profile;
	
	@ManyToOne
	@JoinTable(name="POST_COMMENT",
			   joinColumns=@JoinColumn(name="COMMENT_INDEX"),
			   inverseJoinColumns=@JoinColumn(name="POST_INDEX"))
	private Post post;
	
	@ManyToMany
	@JoinTable(name="COMMENT_RECOMMENT",
			   joinColumns=@JoinColumn(name="COMMENT_INDEX"),
			   inverseJoinColumns=@JoinColumn(name="RECOMMENT_INDEX"))
	private List<Comment> reComments;
	
}
