package com.touchinghand.dto;

import java.io.Serializable;

import com.touchinghand.entity.client.ClientEntity;

public class ClientMse implements Serializable {
	
	private Integer clientMseId;
	private Integer clientId;
	private Client client;
	private String appearance;
	private String otherAppearance;
	private String speech;
	private String otherSpeech;
	private String eyeContact;
	private String otherEyeContact;
	private String motorActivity;
	private String otherMotorActivity;
	private String affect;
	private String otherAffect;
	private String mood;
	private String otherMood;
	private String orientationImpairment;
	private String memoryImpairment;
	private String otherMemoryImpairment;
	private String attention;
	private String cognitionComments;
	private String hallucinations;
	private String otherPerceptions;
	private String perceptionComments;
	private String suicidality;
	private String homicidality;
	private String delusions;
	private String otherDelusions;
	private String thoughtComments;
	private String behavior;
	private String otherBehavior;
	private String behaviorComments;
	private String insight;
	private String otherInsight;
	private String judgement;
	private String otherJudgement;
	
	public Integer getClientMseId() {
		return clientMseId;
	}
	public void setClientMseId(Integer clientMseId) {
		this.clientMseId = clientMseId;
	}
	public Integer getClientId() {
		return clientId;
	}
	public void setClientId(Integer clientId) {
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
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
}
