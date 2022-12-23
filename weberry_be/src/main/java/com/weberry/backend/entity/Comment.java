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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="COMMENT")
@Builder @NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString
public class Comment {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long index;
	
	private String content;
	
	private Date createdAt;
	
	@ManyToOne
	@JoinTable(name="USER_COMMENT",
			   joinColumns=@JoinColumn(name="COMMENT_INDEX"),
			   inverseJoinColumns=@JoinColumn(name="USERID"))
	private User user;
	
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
