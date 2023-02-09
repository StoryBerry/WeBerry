package com.weberry.backend.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
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
	private String farmId;
	
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
		
		private String farmId;
		private String farmName;
		private String local;
		private String city;
		private String address;
		private List<User> users;
		private List<Data> datas;
		
		public static Farm toCreate(Request request) {
			
			return Farm.builder().farmId(request.getCity() + "_" + request.getFarmName())
								 .farmName(request.getFarmName())
								 .local(request.getLocal())
								 .city(request.getCity())
								 .address(request.getAddress())
								 .users(new ArrayList<User>())
								 .datas(new ArrayList<Data>()).build();
		}
	}
	
	@Builder @NoArgsConstructor @AllArgsConstructor
	@Getter @Setter @ToString
	public static class SignIn {
		
		private String farmId;
		private String farmName;
		private String local;
		private String city;
		private String address;
		
		public static SignIn toSignIn(Farm farm) {
			
			return SignIn.builder().farmId(farm.getFarmId())
								   .farmName(farm.getFarmName())
								   .local(farm.getLocal())
								   .city(farm.getCity())
								   .address(farm.getAddress()).build();
		}
		
		public static Farm toFarm(Farm.SignIn farm) {
			
			return Farm.builder().farmId(farm.getFarmId())
								 .farmName(farm.getFarmName())
								 .local(farm.getLocal())
								 .city(farm.getCity())
								 .address(farm.getAddress()).build();
		}
		
	}
	
}
