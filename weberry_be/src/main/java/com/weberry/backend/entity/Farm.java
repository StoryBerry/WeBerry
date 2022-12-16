package com.weberry.backend.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="FARM")
public class Farm {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long index;
	
	@Column(name="FARM_NAME")
	private String farmName;
	
	private String local;
	
	private String city;
	
	private String address;
	
	@OneToMany(mappedBy="farm", cascade=CascadeType.PERSIST)
	private List<User> users;
	
	@OneToMany(mappedBy="farm", cascade=CascadeType.PERSIST)
	private List<Data> datas;
	
}
