package com.touchinghand.entity.client;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="session_record")
public class SessionRecordEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="record_id")
	private int recordId;
	
	@Column(name="session_id")
	private int sessionId;
	
	@ManyToOne
	@JoinColumn(name="session_id", insertable=false, updatable=false)
	private PsySessionEntity sessionEntity;
	
	
	@Column(name="impression")
	private String impression;
	
	@Column(name="feedback")
	private String feedback;
	
	@Column(name="created_on")
	private LocalDateTime createdOn;
	
	@Column(name="updated_on")
	private LocalDateTime updatedOn;

	public int getRecordId() {
		return recordId;
	}

	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}

	public int getSessionId() {
		return sessionId;
	}

	public void setSessionId(int sessionId) {
		this.sessionId = sessionId;
	}

	public PsySessionEntity getSessionEntity() {
		return sessionEntity;
	}

	public void setSessionEntity(PsySessionEntity sessionEntity) {
		this.sessionEntity = sessionEntity;
	}

	public String getImpression() {
		return impression;
	}

	public void setImpression(String impression) {
		this.impression = impression;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}

	public LocalDateTime getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(LocalDateTime updatedOn) {
		this.updatedOn = updatedOn;
	}
	
	

}
