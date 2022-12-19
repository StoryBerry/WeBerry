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

import org.hibernate.annotations.ColumnDefault;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="POST")
@Builder @NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString
public class Post {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long index;
	
	private String content;
	
//	private ?? images;
	
	private Date createdAt;
	
	@ColumnDefault("0")
	private long likes;
	
	@ManyToOne
	@JoinTable(name="PROFILE_POST",
			   joinColumns=@JoinColumn(name="POST_INDEX"),
			   inverseJoinColumns=@JoinColumn(name="PROFILE_INDEX"))
	private Profile profile;
	
	@OneToMany(mappedBy="post")
	private List<Comment> comments;
	
}
