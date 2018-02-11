package com.touchinghand.entity.session;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.touchinghand.entity.client.ClientEntity;

@Entity
@Table(name="session")
public class PsySessionEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="session_id")
	private Integer sessionId;
	
	@OneToOne(mappedBy="sessionEntity")
	private SessionRecordEntity sessionRecordEntity;
	
	@Column(name="client_id")
	private Integer clientId;
	
	@ManyToOne
	@JoinColumn(name="client_id", insertable=false, updatable=false)
	private ClientEntity clientEntity;
	
	@Column(name="session_date")
	private LocalDate sessionDate;
	
	@Column(name="followup_date")
	private LocalDate followupDate;
	
	@Column(name="created_on")
	private LocalDateTime createdOn;
	
	@Column(name="updated_on")
	private LocalDateTime updatedOn;

	public Integer getSessionId() {
		return sessionId;
	}

	public void setSessionId(Integer sessionId) {
		this.sessionId = sessionId;
	}

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public ClientEntity getClientEntity() {
		return clientEntity;
	}

	public void setClientEntity(ClientEntity clientEntity) {
		this.clientEntity = clientEntity;
	}

	public LocalDate getSessionDate() {
		return sessionDate;
	}

	public void setSessionDate(LocalDate sessionDate) {
		this.sessionDate = sessionDate;
	}

	public LocalDate getFollowupDate() {
		return followupDate;
	}

	public void setFollowupDate(LocalDate followupDate) {
		this.followupDate = followupDate;
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

	public SessionRecordEntity getSessionRecordEntity() {
		return sessionRecordEntity;
	}

	public void setSessionRecordEntity(SessionRecordEntity sessionRecordEntity) {
		this.sessionRecordEntity = sessionRecordEntity;
	}

}
