package com.weberry.backend.entity;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

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
	private long id; 
	
	private String imageUrl;
	
	private float temperature;
	
	private float humidity;
	
	private LocalDate mDate;
	
	private int point;
	
	@ManyToOne
	@JoinTable(name="FARM_DATA",
				joinColumns=@JoinColumn(name="DATA_INDEX"),
				inverseJoinColumns=@JoinColumn(name="FARM_INDEX"))
	private Farm farm;
	
	@Builder @NoArgsConstructor @AllArgsConstructor
	@Getter @Setter @ToString
	public static class Request {
		
		private float temperature;
		private float humidity;
		@DateTimeFormat(pattern="yyyy-MM-dd")
	    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="Asia/Seoul")
		private LocalDate mDate;
		private int point;
		private Farm farm;
		
		public static Data toCreate(Request request) {
			
			return Data.builder().temperature(request.getTemperature())
								 .humidity(request.getHumidity())
								 .mDate(request.getMDate())
								 .point(request.getPoint())
								 .farm(request.getFarm()).build();
		}
		
	}
	
}
