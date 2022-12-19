package com.weberry.backend.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="FARM")
@Builder @NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString
public class Farm {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long index;
	
	@Column(name="FARM_NAME")
	private String farmName;
	
	private String local;
	
	private String city;
	
	private String address;
	
	@OneToMany(mappedBy="farm")
	private List<User> users;
	
	@OneToMany(mappedBy="farm")
	private List<Data> datas;
	
	@Builder @NoArgsConstructor @AllArgsConstructor
	@Getter @Setter @ToString
	public static class Request {
		
		private String farmName;
		private String local;
		private String city;
		private String address;
		private List<User> users;
		private List<Data> datas;
		
		public static Farm toCreate(Request request) {
			
			return Farm.builder().farmName(request.getFarmName())
								 .local(request.getLocal())
								 .city(request.getCity())
								 .address(request.getAddress())
								 .users(new ArrayList<User>())
								 .datas(new ArrayList<Data>()).build();
		}
	}
	
	@Builder @NoArgsConstructor @AllArgsConstructor
	@Getter @Setter @ToString
	public static class Response {
		
		private String farmName;
		private String local;
		private String city;
		private String address;
		private List<User.Response> users;
		private List<Data> datas;
		
	}
	
}
