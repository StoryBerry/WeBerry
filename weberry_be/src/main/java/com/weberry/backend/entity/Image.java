package com.weberry.backend.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="IMAGE")
@Builder @NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString
public class Image {
	
	@Id
	private String imageUrl;
	
	@OneToOne
	@JoinTable(name="DATA_IMAGE",
			   joinColumns=@JoinColumn(name="IMAGE_URL"),
			   inverseJoinColumns=@JoinColumn(name="DATA_ID"))
	private Data data;
	
	@ManyToOne
	@JoinTable(name="REPORT_IMAGE",
			   joinColumns=@JoinColumn(name="IMAGE_URL"),
			   inverseJoinColumns=@JoinColumn(name="REPORT_ID"))
	private Report report;
	
	@ManyToOne
	@JoinTable(name="POST_IMAGE",
			   joinColumns=@JoinColumn(name="IMAGE_URL"),
			   inverseJoinColumns=@JoinColumn(name="POST_ID"))
	private Post post;
	
	@ManyToOne
	@JoinTable(name="CHAT_IMAGE",
			   joinColumns=@JoinColumn(name="IMAGE_URL"),
			   inverseJoinColumns=@JoinColumn(name="CHAT_ID"))
	private Chat chat;
	
	public Data setData(Data data) {
		data.setImageUrl(this);
		
		return data;
	}
	
	public Report setReportBaseImageUrl(Report report) {
		report.getBaseImageUrl().add(this);
		
		return report;
	}
	
	public Report setReportAnalyzedImageUrl(Report report) {
		report.getAnalyezedImageUrl().add(this);
		
		return report;
	}
	
	public Post setPost(Post post) {
		System.out.println("imagelist_size: " + post.getImages().size());
		post.getImages().add(this);
		
		return post;
	}
	
	public Chat setChat(Chat chat) {
		chat.getImages().add(this);
		
		return chat;
	}
	
	@Builder @NoArgsConstructor @AllArgsConstructor
	@Getter @Setter @ToString
	public static class Request {
		
		private String imageUrl;
		private Data data;
		private Report report;
		private Post post;
		private Chat chat;
		
		public static Image toImage(String url, Data data) {
			
			return Image.builder().imageUrl(url)
								  .data(data).build();
		}
		
		public static Image toBaseImage(String url, Report report) {
			
			return Image.builder().imageUrl(url)
								  .report(report)
								  .data(report.getData()).build();
		}
		
		public static Image toAnalyzedImage(String url, Report report) {
			
			return Image.builder().imageUrl(url)
					.report(report).build();
		}
		
		public static Image toImage(String url, Post post) {
			
			return Image.builder().imageUrl(url)
					.post(post).build();
		}
		
		public static Image toImage(String url, Chat chat) {
			
			return Image.builder().imageUrl(url)
					.chat(chat).build();
		}
	}
	
	@Builder @NoArgsConstructor @AllArgsConstructor
	@Getter @Setter @ToString
	public static class ToShow {
		
		private String imageUrl;
		
		public static ToShow toShow(Image image) {
			
			if (image == null) return null;
			
			return ToShow.builder().imageUrl(image.getImageUrl()).build();
		}
	}
	
}
