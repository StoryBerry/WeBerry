package com.weberry.backend.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
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
	
	@OneToMany(mappedBy="report")
	private List<Image> imageUrls;
	
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
		
		public static Report toReport(Request request, Data data) {
			
			return Report.builder().id(request.getId())
								   .status(request.getStatus())
								   .imageUrls(new ArrayList<Image>())
								   .data(data)
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
			if (report.getImageUrls().size() == 2) {
				
				return Report.ToShow.builder()
									.id(report.getData().getId())
									.status(report.getStatus())
									.baseImageUrl(Image.ToShow.toShow(report.getImageUrls().get(1)))
									.analyzedImageUrl(Image.ToShow.toShow(report.getImageUrls().get(0)))
									.data(Data.ToShow.withoutImage(report.getData()))
									.build();
			} else {
				
				return Report.ToShow.builder()
									.id(report.getData().getId())
									.status(report.getStatus())
									.baseImageUrl(Image.ToShow.toShow(report.getImageUrls().get(0)))
									.data(Data.ToShow.withoutImage(report.getData()))
									.build();
				
			}
			
		}
	}
}
