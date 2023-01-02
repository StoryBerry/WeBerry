package com.weberry.backend.entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
	private String id; 
	
	private String imageUrl;
	
	private float temperature;
	
	private float humidity;
	
	private LocalDate mDate;
	
	private int point;
	
	@ManyToOne
	@JoinTable(name="FARM_DATA",
				joinColumns=@JoinColumn(name="DATA_ID"),
				inverseJoinColumns=@JoinColumn(name="FARM_ID"))
	@JsonIgnore
	private Farm farm;
	
	@OneToOne(mappedBy="data")
	@JsonIgnore
	private Report report;
	
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
			DateTimeFormatter format = DateTimeFormatter.ofPattern("yyMMdd");
			String id = String.format("%s_%s_%d", request.getFarm().getFarmId(), request.getMDate().format(format), request.getPoint());
			
			return Data.builder().id(id)
								 .temperature(request.getTemperature())
								 .humidity(request.getHumidity())
								 .mDate(request.getMDate())
								 .point(request.getPoint())
								 .farm(request.getFarm()).build();
		}
	}
	
	@Builder @NoArgsConstructor @AllArgsConstructor
	@Getter @Setter @ToString
	public static class ToShow {
		
		private String id; 
		private String imageUrl;
		private float temperature;
		private float humidity;
		private LocalDate mDate;
		private int point;
		private Farm.SignIn farm;
		
		public static ToShow toShow(Data data) {
			
			return ToShow.builder()
						 .id(data.getId())
						 .imageUrl(data.getImageUrl())
						 .temperature(data.getTemperature())
						 .humidity(data.getHumidity())
						 .mDate(data.getMDate())
						 .point(data.getPoint())
						 .farm(Farm.SignIn.toSignIn(data.getFarm()))
						 .build();
		}
	}
}
