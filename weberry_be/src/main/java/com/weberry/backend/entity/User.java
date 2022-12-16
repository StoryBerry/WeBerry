package com.weberry.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="USERS")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long index;
	
	private String userid;
	
	private String password;
	
	@Column(columnDefinition="boolean default false")
	private boolean admin;
	
	private String login;
	
	@ManyToOne
	@JoinTable(name="FARM_USER",
			   joinColumns=@JoinColumn(name="USER_INDEX"),
			   inverseJoinColumns=@JoinColumn(name="FARM_INDEX"))
	private Farm farm;
	
	@OneToOne(mappedBy="user")
	private Profile profile;
	
}
