package com.touchinghand.entity.client;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="client_mse")
public class ClientMseEntity {

	
	@Column(name="client_mse_id")
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int clientMseId;
	
	@Column(name="client_id")
	private int clientId;
	
	@OneToOne
	@JoinColumn(name="client_id", insertable=false, updatable=false)
	private ClientEntity clientEntity;
	
	@Column(name="appearance")
	private String appearance;
	
	@Column(name="other_appearance")
	private String otherAppearance;
	
	@Column(name="speech")
	private String speech;
	
	@Column(name="other_speech")
	private String otherSpeech;
	
	@Column(name="eye_contact")
	private String eyeContact;
	
	@Column(name="other_eyecontact")
	private String otherEyeContact;
	
	@Column(name="motor_activity")
	private String motorActivity;
	
	@Column(name="other_motoractivity")
	private String otherMotorActivity;
	
	@Column(name="affect")
	private String affect;
	
	@Column(name="other_affect")
	private String otherAffect;
	
	@Column(name="mood")
	private String mood;
	
	@Column(name="other_mood")
	private String otherMood;
	
	@Column(name="orientation_impairment")
	private String orientationImpairment;
	
	@Column(name="memory_impairment")
	private String memoryImpairment;
	
	@Column(name="other_memory_impairment")
	private String otherMemoryImpairment;
	
	@Column(name="attention")
	private String attention;
	
	@Column(name="cognition_comments")
	private String cognitionComments;
	
	@Column(name="hallucinations")
	private String hallucinations;
	
	@Column(name="other_perceptions")
	private String otherPerceptions;
	
	@Column(name="perceptions_comments")
	private String perceptionComments;
	
	@Column(name="suicidality")
	private String suicidality;
	
	@Column(name="homicidality")
	private String homicidality;
	
	@Column(name="delusions")
	private String delusions;
	
	@Column(name="other_delusions")
	private String otherDelusions;
	
	@Column(name="thoughts_comments")
	private String thoughtComments;
	
	@Column(name="behavior")
	private String behavior;
	
	@Column(name="other_behavior")
	private String otherBehavior;
	
	@Column(name="behavior_comments")
	private String behaviorComments;
	
	@Column(name="insight")
	private String insight;
	
	@Column(name="other_insight")
	private String otherInsight;
	
	@Column(name="judgement")
	private String judgement;
	
	@Column(name="judgement_other")
	private String otherJudgement;

	public int getClientMseId() {
		return clientMseId;
	}

	public void setClientMseId(int clientMseId) {
		this.clientMseId = clientMseId;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public String getAppearance() {
		return appearance;
	}

	public void setAppearance(String appearance) {
		this.appearance = appearance;
	}

	public String getOtherAppearance() {
		return otherAppearance;
	}

	public void setOtherAppearance(String otherAppearance) {
		this.otherAppearance = otherAppearance;
	}

	public String getSpeech() {
		return speech;
	}

	public void setSpeech(String speech) {
		this.speech = speech;
	}

	public String getOtherSpeech() {
		return otherSpeech;
	}

	public void setOtherSpeech(String otherSpeech) {
		this.otherSpeech = otherSpeech;
	}

	public String getEyeContact() {
		return eyeContact;
	}

	public void setEyeContact(String eyeContact) {
		this.eyeContact = eyeContact;
	}

	public String getOtherEyeContact() {
		return otherEyeContact;
	}

	public void setOtherEyeContact(String otherEyeContact) {
		this.otherEyeContact = otherEyeContact;
	}

	public String getMotorActivity() {
		return motorActivity;
	}

	public void setMotorActivity(String motorActivity) {
		this.motorActivity = motorActivity;
	}

	public String getOtherMotorActivity() {
		return otherMotorActivity;
	}

	public void setOtherMotorActivity(String otherMotorActivity) {
		this.otherMotorActivity = otherMotorActivity;
	}

	public String getAffect() {
		return affect;
	}

	public void setAffect(String affect) {
		this.affect = affect;
	}

	public String getOtherAffect() {
		return otherAffect;
	}

	public void setOtherAffect(String otherAffect) {
		this.otherAffect = otherAffect;
	}

	public String getMood() {
		return mood;
	}

	public void setMood(String mood) {
		this.mood = mood;
	}

	public String getOtherMood() {
		return otherMood;
	}

	public void setOtherMood(String otherMood) {
		this.otherMood = otherMood;
	}

	public String getOrientationImpairment() {
		return orientationImpairment;
	}

	public void setOrientationImpairment(String orientationImpairment) {
		this.orientationImpairment = orientationImpairment;
	}

	public String getMemoryImpairment() {
		return memoryImpairment;
	}

	public void setMemoryImpairment(String memoryImpairment) {
		this.memoryImpairment = memoryImpairment;
	}

	public String getOtherMemoryImpairment() {
		return otherMemoryImpairment;
	}

	public void setOtherMemoryImpairment(String otherMemoryImpairment) {
		this.otherMemoryImpairment = otherMemoryImpairment;
	}

	public String getAttention() {
		return attention;
	}

	public void setAttention(String attention) {
		this.attention = attention;
	}

	public String getCognitionComments() {
		return cognitionComments;
	}

	public void setCognitionComments(String cognitionComments) {
		this.cognitionComments = cognitionComments;
	}

	public String getHallucinations() {
		return hallucinations;
	}

	public void setHallucinations(String hallucinations) {
		this.hallucinations = hallucinations;
	}

	public String getOtherPerceptions() {
		return otherPerceptions;
	}

	public void setOtherPerceptions(String otherPerceptions) {
		this.otherPerceptions = otherPerceptions;
	}

	public String getPerceptionComments() {
		return perceptionComments;
	}

	public void setPerceptionComments(String perceptionComments) {
		this.perceptionComments = perceptionComments;
	}

	public String getSuicidality() {
		return suicidality;
	}

	public void setSuicidality(String suicidality) {
		this.suicidality = suicidality;
	}

	public String getHomicidality() {
		return homicidality;
	}

	public void setHomicidality(String homicidality) {
		this.homicidality = homicidality;
	}

	public String getDelusions() {
		return delusions;
	}

	public void setDelusions(String delusions) {
		this.delusions = delusions;
	}

	public String getOtherDelusions() {
		return otherDelusions;
	}

	public void setOtherDelusions(String otherDelusions) {
		this.otherDelusions = otherDelusions;
	}

	public String getThoughtComments() {
		return thoughtComments;
	}

	public void setThoughtComments(String thoughtComments) {
		this.thoughtComments = thoughtComments;
	}

	public String getBehavior() {
		return behavior;
	}

	public void setBehavior(String behavior) {
		this.behavior = behavior;
	}

	public String getOtherBehavior() {
		return otherBehavior;
	}

	public void setOtherBehavior(String otherBehavior) {
		this.otherBehavior = otherBehavior;
	}

	public String getBehaviorComments() {
		return behaviorComments;
	}

	public void setBehaviorComments(String behaviorComments) {
		this.behaviorComments = behaviorComments;
	}

	public String getInsight() {
		return insight;
	}

	public void setInsight(String insight) {
		this.insight = insight;
	}

	public String getOtherInsight() {
		return otherInsight;
	}

	public void setOtherInsight(String otherInsight) {
		this.otherInsight = otherInsight;
	}

	public String getJudgement() {
		return judgement;
	}

	public void setJudgement(String judgement) {
		this.judgement = judgement;
	}

	public String getOtherJudgement() {
		return otherJudgement;
	}

	public void setOtherJudgement(String otherJudgement) {
		this.otherJudgement = otherJudgement;
	}

	public ClientEntity getClientEntity() {
		return clientEntity;
	}

	public void setClientEntity(ClientEntity clientEntity) {
		this.clientEntity = clientEntity;
	}
	
}


