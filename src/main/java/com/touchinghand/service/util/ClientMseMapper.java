package com.touchinghand.service.util;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.cxf.common.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.touchinghand.common.CommonUtils;
import com.touchinghand.common.DateResolver;
import com.touchinghand.dto.ClientMse;
import com.touchinghand.entity.client.ClientMseEntity;

@Component
public class ClientMseMapper {
	
	@Autowired
	private DateResolver dateResolver;
	
	@Autowired
	private CommonUtils utils;
	
	
	public List<ClientMseEntity> toEntities(List<ClientMse> cms){
		if(CollectionUtils.isEmpty(cms)) return null;
		return cms.stream().map(this::toEntity).collect(Collectors.toList());
	}
	
	public List<ClientMse> fromEntities(List<ClientMseEntity> cms){
		if(CollectionUtils.isEmpty(cms)) return null;
		return cms.stream().map(this::fromEntity).collect(Collectors.toList());
	}

	public ClientMseEntity toEntity(ClientMse cm) {
		if(cm == null) return null;
		ClientMseEntity cme = new ClientMseEntity();
		setClientMseEntityAttribs(cm, cme);
		cme.setCreatedOn(LocalDateTime.now());
		return cme;
	}

	private void setClientMseEntityAttribs(ClientMse cm, ClientMseEntity cme) {
		if(utils.changed(cme.getAffect(), cm.getAffect()))
			cme.setAffect(cm.getAffect());
		if(utils.changed(cme.getAppearance(), cm.getAppearance()))
			cme.setAppearance(cm.getAppearance());
		if(utils.changed(cme.getAttention(), cm.getAttention()))
			cme.setAttention(cm.getAttention());
		if(utils.changed(cme.getBehavior(), cm.getBehavior()))
			cme.setBehavior(cm.getBehavior());
		if(utils.changed(cme.getBehaviorComments(), cm.getBehaviorComments()))
			cme.setBehaviorComments(cm.getBehaviorComments());
		if(utils.changed(cme.getClientId(), cm.getClientId()))
			cme.setClientId(cm.getClientId());
		if(utils.changed(cme.getCognitionComments(), cm.getCognitionComments()))
			cme.setCognitionComments(cm.getCognitionComments());
		if(utils.changed(cme.getDelusions(), cm.getDelusions()))
			cme.setDelusions(cm.getDelusions());
		if(utils.changed(cme.getEyeContact(), cm.getEyeContact()))
			cme.setEyeContact(cm.getEyeContact());
		if(utils.changed(cme.getHallucinations(), cm.getHallucinations()))
			cme.setHallucinations(cm.getHallucinations());
		if(utils.changed(cme.getHomicidality(), cm.getHomicidality()))
			cme.setHomicidality(cm.getHomicidality());
		if(utils.changed(cme.getInsight(), cm.getInsight()))
			cme.setInsight(cm.getInsight());
		if(utils.changed(cme.getJudgement(), cm.getJudgement()))
			cme.setJudgement(cm.getJudgement());
		if(utils.changed(cme.getMemoryImpairment(), cm.getMemoryImpairment()))
			cme.setMemoryImpairment(cm.getMemoryImpairment());
		if(utils.changed(cme.getMood(), cm.getMood()))
			cme.setMood(cm.getMood());
		if(utils.changed(cme.getMotorActivity(), cm.getMotorActivity()))
			cme.setMotorActivity(cm.getMotorActivity());
		if(utils.changed(cme.getOrientationImpairment(), cm.getOrientationImpairment()))
			cme.setOrientationImpairment(cm.getOrientationImpairment());
		if(utils.changed(cme.getOtherPerceptions(), cm.getOtherPerceptions()))
			cme.setOtherPerceptions(cm.getOtherPerceptions());
		if(utils.changed(cme.getSpeech(), cm.getSpeech()))
			cme.setSpeech(cm.getSpeech());
		if(utils.changed(cme.getSuicidality(), cm.getSuicidality()))
			cme.setSuicidality(cm.getSuicidality());
		if(utils.changed(cme.getThoughtComments(), cm.getThoughtComments()))
			cme.setThoughtComments(cm.getThoughtComments());
	}
	
	public ClientMse fromEntity(ClientMseEntity cme) {
		if(cme == null) return null;
		ClientMse cm = new ClientMse();
		cm.setClientMseId(cme.getClientMseId());
		cm.setAffect(cme.getAffect());
		cm.setAppearance(cme.getAppearance());
		cm.setAttention(cme.getAttention());
		cm.setBehavior(cme.getBehavior());
		cm.setBehaviorComments(cme.getBehaviorComments());
		cm.setClientId(cme.getClientId());
		cm.setCognitionComments(cme.getCognitionComments());
		cm.setDelusions(cme.getDelusions());
		cm.setEyeContact(cme.getEyeContact());
		cm.setHallucinations(cme.getHallucinations());
		cm.setHomicidality(cme.getHomicidality());
		cm.setInsight(cme.getInsight());
		cm.setJudgement(cme.getJudgement());
		cm.setMemoryImpairment(cme.getMemoryImpairment());
		cm.setMood(cme.getMood());
		cm.setMotorActivity(cme.getMotorActivity());
		cm.setOrientationImpairment(cme.getOrientationImpairment());
		cm.setSpeech(cme.getSpeech());
		cm.setSuicidality(cme.getSuicidality());
		cm.setThoughtComments(cme.getThoughtComments());
		cm.setCreationDate(dateResolver.toStringDate(cme.getCreatedOn()));
		/*if(cme.getClientEntity() != null) {
			cm.setClient(clientMapper.fromEntity(cme.getClientEntity()));
		}*/
		return cm;
				
	}
	
	public ClientMseEntity toEntity(ClientMseEntity cme, ClientMse cm) {
		if( cme == null || cm == null) return null;
		setClientMseEntityAttribs(cm, cme);
		return cme;
	}
	
}
