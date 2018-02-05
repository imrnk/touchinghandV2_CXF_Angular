package com.touchinghand.entity.client;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="treatment_data")
public class TreatmentDataEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="treatment_id")
	private int treatmentId;
	
	@Column(name="session_id")
	private int sessionId;
	
	@OneToOne
	@JoinColumn(name="session_id", insertable=false, updatable=false)
	private PsySessionEntity sessionEntity;
	
	@Column(name="physical_comp")
	private String physicalComp;
	
	@Column(name="mental_comp")
	private String mentalComp;
	
	@Column(name="onset_date")
	private LocalDate onsetDate;
	
	@Column(name="duration")
	private String duration;
	
	@Column(name="degree")
	private Integer degree;
	
	@Column(name="therapy_applied")
	private String therapyApplied;
	
	@Column(name="casehistory")
	private String casehistory;
	
	@Column(name="psy_evaluation")
	private String psyEvaluation;
	
	@Column(name="lab_testing")
	private String labTesting;
	
	@Column(name="diagnosis")
	private String diagnosis;
	
	@Column(name="diff_diagnosis")
	private String diffDiagnosis;
	
	@Column(name="formulation")
	private String formulation;
	
	@Column(name="feedback")
	private String feedback;
	
	@Column(name="created_on")
	private LocalDateTime createdOn;
	
	@Column(name="updated_on")
	private LocalDateTime updatedOn;

	public int getTreatmentId() {
		return treatmentId;
	}

	public void setTreatmentId(int treatmentId) {
		this.treatmentId = treatmentId;
	}

	public int getSessionId() {
		return sessionId;
	}

	public void setSessionId(int sessionId) {
		this.sessionId = sessionId;
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

	public Integer getDegree() {
		return degree;
	}

	public void setDegree(Integer degree) {
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
