package com.touchinghand.entity.session;

import java.io.Serializable;
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

import com.touchinghand.entity.client.ClientEntity;

@Entity
@Table(name="treatment_data")
public class TreatmentDataEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1806005437826125607L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="treatment_id")
	private Integer treatmentId;
	
	@Column(name="session_id")
	private Integer sessionId;
	
	@Column(name="client_id")
	private Integer clientId;
	
	@OneToOne
	@JoinColumn(name="session_id", insertable=false, updatable=false)
	private PsySessionEntity sessionEntity;
	
	@OneToOne(optional=true)
	@JoinColumn(name="client_id", insertable=false, updatable=false)
	private ClientEntity clientEntity;
	
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
	
	@Column(name="client_doc_link")
	private String clientDocLink;
	
	@Column(name="created_on")
	private LocalDateTime createdOn;
	
	@Column(name="updated_on")
	private LocalDateTime updatedOn;

	public Integer getTreatmentId() {
		return treatmentId;
	}

	public void setTreatmentId(Integer treatmentId) {
		this.treatmentId = treatmentId;
	}

	public Integer getSessionId() {
		return sessionId;
	}

	public void setSessionId(Integer sessionId) {
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
	

	public String getClientDocLink() {
		return clientDocLink;
	}

	public void setClientDocLink(String clientDocLink) {
		this.clientDocLink = clientDocLink;
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

	public ClientEntity getClientEntity() {
		return clientEntity;
	}

	public void setClientEntity(ClientEntity clientEntity) {
		this.clientEntity = clientEntity;
	}

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((clientId == null) ? 0 : clientId.hashCode());
		result = prime * result + ((sessionId == null) ? 0 : sessionId.hashCode());
		result = prime * result + ((treatmentId == null) ? 0 : treatmentId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TreatmentDataEntity other = (TreatmentDataEntity) obj;
		if (clientId == null) {
			if (other.clientId != null)
				return false;
		} else if (!clientId.equals(other.clientId))
			return false;
		if (sessionId == null) {
			if (other.sessionId != null)
				return false;
		} else if (!sessionId.equals(other.sessionId))
			return false;
		if (treatmentId == null) {
			if (other.treatmentId != null)
				return false;
		} else if (!treatmentId.equals(other.treatmentId))
			return false;
		return true;
	}

}
