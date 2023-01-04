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
	private String imagUrl;
	
	@OneToOne
	@JoinTable(name="DATA_IMAGE",
			   joinColumns=@JoinColumn("IMAGE_URL"),
			   inverseJoinColumns=@JoinColumn("DATA_ID"))
	private Data data;
	
	@OneToOne
	@JoinTable(name="REPORT_IMAGE",
			   joinColumns=@JoinColumn("IMAGE_URL"),
			   inverseJoinColumns=@JoinColumn("REPORT_ID"))
	private Report report;
	
	@ManyToOne
	@JoinTable(name="POST_IMAGE",
			   joinColumns=@JoinColumn("IMAGE_URL"),
			   inverseJoinColumns=@JoinColumn("POST_ID"))
	private Post post;
	
	@ManyToOne
	@JoinTable(name="CHAT_IMAGE",
			   joinColumns=@JoinColumn("IMAGE_URL"),
			   inverseJoinColumns=@JoinColumn("CHAT_ID"))
	private Chat chat;
	
	@Builder @NoArgsConstructor @AllArgsConstructor
	@Getter @Setter @ToString
	public static class ToShow {
		
		private String imageUrl;
		
		public static ToShow toShow(Image image) {
			
			return ToShow.builder().imageUrl(image.getImagUrl()).build();
		}
	}
	
}
