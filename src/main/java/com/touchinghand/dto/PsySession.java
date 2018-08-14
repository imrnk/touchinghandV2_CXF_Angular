package com.touchinghand.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public class PsySession implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8378083496382765011L;

	private String sessionId;
	
	private Integer clientId;
	
	private Client client;
	
	private TreatmentData treatmentData;
	
	public TreatmentData getTreatmentData() {
		return treatmentData;
	}

	public void setTreatmentData(TreatmentData treatmentData) {
		this.treatmentData = treatmentData;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	private String sessionDate;
	
	private String followupDate;
	
	private String impression;
	
	private String feedback;
	
	private LocalDateTime createdOn;
	
	private LocalDateTime updatedOn;

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public String getSessionDate() {
		return sessionDate;
	}

	public void setSessionDate(String sessionDate) {
		this.sessionDate = sessionDate;
	}

	public String getFollowupDate() {
		return followupDate;
	}

	public void setFollowupDate(String followupDate) {
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
	
	
}
