package com.touchinghand.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public class TreatmentData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7040316841254479991L;

	private Integer treatmentId;
	
	private String sessionId;
	
	private String clientId;
	
	private PsySession session;
	
	private String physicalComp;
	
	private String mentalComp;
	
	private String onsetDate;
	
	private String duration;
	
	private String degree;
	
	private String therapyApplied;
	
	private String casehistory;
	
	private String psyEvaluation;
	
	private String labTesting;
	
	private String diagnosis;
	
	private String diffDiagnosis;
	
	private String formulation;
	
	private String clientDocLink;
	
	private LocalDateTime createdOn;
	
	private LocalDateTime updatedOn;

	public Integer getTreatmentId() {
		return treatmentId;
	}

	public void setTreatmentId(Integer treatmentId) {
		this.treatmentId = treatmentId;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public PsySession getSession() {
		return session;
	}

	public void setSession(PsySession session) {
		this.session = session;
	}

	public String getPhysicalComp() {
		return physicalComp;
	}

	public void setPhysicalComp(String physicalComp) {
		this.physicalComp = physicalComp;
	}

	public String getMentalComp() {
		return mentalComp;
	}

	public void setMentalComp(String mentalComp) {
		this.mentalComp = mentalComp;
	}

	public String getOnsetDate() {
		return onsetDate;
	}

	public void setOnsetDate(String onsetDate) {
		this.onsetDate = onsetDate;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getTherapyApplied() {
		return therapyApplied;
	}

	public void setTherapyApplied(String therapyApplied) {
		this.therapyApplied = therapyApplied;
	}

	public String getCasehistory() {
		return casehistory;
	}

	public void setCasehistory(String casehistory) {
		this.casehistory = casehistory;
	}

	public String getPsyEvaluation() {
		return psyEvaluation;
	}

	public void setPsyEvaluation(String psyEvaluation) {
		this.psyEvaluation = psyEvaluation;
	}

	public String getLabTesting() {
		return labTesting;
	}

	public void setLabTesting(String labTesting) {
		this.labTesting = labTesting;
	}

	public String getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	public String getDiffDiagnosis() {
		return diffDiagnosis;
	}

	public void setDiffDiagnosis(String diffDiagnosis) {
		this.diffDiagnosis = diffDiagnosis;
	}

	public String getFormulation() {
		return formulation;
	}

	public void setFormulation(String formulation) {
		this.formulation = formulation;
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

	public String getClientDocLink() {
		return clientDocLink;
	}

	public void setClientDocLink(String clientDocLink) {
		this.clientDocLink = clientDocLink;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	
}
