package com.weberry.backend.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="Report")
@Builder @NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString
public class Report {
	
	@Id
	private String id;
	
	private String status;
	
	@OneToOne(mappedBy="report")
	private Image baseImageUrl;
	
	@OneToOne(mappedBy="report")
	private Image analyzedImageUrl;
	
	@OneToOne
	@JoinTable(name="DATA_REPORT",
			   joinColumns=@JoinColumn(name="REPORT_ID"),
			   inverseJoinColumns=@JoinColumn(name="DATA_ID"))
	private Data data;
	
	public Data setData(Data data) {
		data.setReport(this);
		
		return data;
	}
	
	@Builder @NoArgsConstructor @AllArgsConstructor
	@Getter @Setter @ToString
	public static class Request {
		
		private String id;
		private String status;
		private Data data;
		
		public static Report toReport(Request request) {
			
			return Report.builder().id(request.getData().getId())
								   .status(request.getStatus())
								   .data(request.getData())
								   .build();
		}
	}
	
	@Builder @NoArgsConstructor @AllArgsConstructor
	@Getter @Setter @ToString
	public static class ToShow {
		
		private String id;
		private String status;
		private Image.ToShow baseImageUrl;
		private Image.ToShow analyzedImageUrl;
		private Data.ToShow data;
		
		public static ToShow toShow(Report report) {
			
			return Report.ToShow.builder()
								.id(report.getData().getId())
								.status(report.getStatus())
								.baseImageUrl(Image.ToShow.toShow(report.getBaseImageUrl()))
								.analyzedImageUrl(Image.ToShow.toShow(report.getAnalyzedImageUrl()))
								.data(Data.ToShow.toShow(report.getData()))
								.build();
		}
	}
}
