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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.weberry.backend.projection.ProfileBasicResponseProjection;

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
	@GeneratedValue(strategy=GenerationType.IDENTITY)
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
	@JsonIgnore
	private Farm farm;
	
	@OneToOne(mappedBy="user")
	private Profile profile;
	
	@Builder @NoArgsConstructor @AllArgsConstructor
	@Getter @Setter @ToString
	public static class Request {

		private String userid;
		private String password;
		private Farm farm;
		private Profile profile;
		
		public static User toCreate(Request request) {
			
			return User.builder().userid(request.getUserid())
								 .password(request.getPassword()).build();
		}
		
	}
	
	@Builder @NoArgsConstructor @AllArgsConstructor
	@Getter @Setter @ToString
	public static class Response {
		
		private String userid;
		private String password;
		private Farm farm;
		private Profile.BasicResponse profile;
		
	}
	
//	@Builder @NoArgsConstructor @AllArgsConstructor
//	@Getter @Setter @ToString
//	public static class Response {
//		
//		private String userid;
//		private String password;
//		private Farm farm;
//		private ProfileBasicResponseProjection profile;
//		
//	}
	
}
