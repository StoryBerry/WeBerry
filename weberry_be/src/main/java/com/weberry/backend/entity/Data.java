package com.weberry.backend.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="DATA")
@Builder @NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString
public class Data {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long index; 
	
//	private ?? image;
	
	private float temperature;
	
	private float humidity;
	
	private int co2;
	
	private Date mDate;
	
	private int coordinateX;
	
	private int coordinateY;
	
	@ManyToOne
	@JoinTable(name="FARM_DATA",
				joinColumns=@JoinColumn(name="DATA_INDEX"),
				inverseJoinColumns=@JoinColumn(name="FARM_INDEX"))
	private Farm farm;
	
}
