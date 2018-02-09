package com.touchinghand.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TreatmentData {

private int treatmentId;
	
	private String sessionId;
	
	private PsySession session;
	
	private String physicalComp;
	
	private String mentalComp;
	
	private LocalDate onsetDate;
	
	private String duration;
	
	private String degree;
	
	private String therapyApplied;
	
	private String casehistory;
	
	private String psyEvaluation;
	
	private String labTesting;
	
	private String diagnosis;
	
	private String diffDiagnosis;
	
	private String formulation;
	
	
	private LocalDateTime createdOn;
	
	private LocalDateTime updatedOn;

	public int getTreatmentId() {
		return treatmentId;
	}

	public void setTreatmentId(int treatmentId) {
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

	public LocalDate getOnsetDate() {
		return onsetDate;
	}

	public void setOnsetDate(LocalDate onsetDate) {
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
	
}
