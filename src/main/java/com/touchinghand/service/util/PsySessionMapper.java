package com.touchinghand.service.util;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.touchinghand.common.CommonUtils;
import com.touchinghand.common.DateResolver;
import com.touchinghand.dto.PsySession;
import com.touchinghand.dto.TreatmentData;
import com.touchinghand.entity.session.PsySessionEntity;
import com.touchinghand.entity.session.SessionRecordEntity;
import com.touchinghand.entity.session.TreatmentDataEntity;

@Component
public class PsySessionMapper {

	@Autowired
	private DateResolver dateResolver;
	
	@Autowired
	private CommonUtils utils;
	
	@Autowired
	private ClientMapper clientMapper;
	
	
	public List<PsySession> fromEntities(List<PsySessionEntity> pses){
		return pses.stream().map(this::fromEntity).collect(Collectors.toList());
	}
	
	public PsySession fromEntity(PsySessionEntity pse) {
		if(pse == null) return null;
		
		PsySession dto = new PsySession();
		dto.setSessionId(String.valueOf(pse.getSessionId()));
		dto.setClientId(pse.getClientId());
		dto.setSessionDate(dateResolver.toStringDate(pse.getSessionDate()));
		dto.setImpression(pse.getSessionRecordEntity().getImpression());
		dto.setFeedback(pse.getSessionRecordEntity().getFeedback());
		dto.setFollowupDate(dateResolver.toStringDate(pse.getFollowupDate()));
		
		dto.setClient(clientMapper.fromEntity(pse.getClientEntity()));
		
		return dto;
		
	}
	
	public PsySessionEntity toEntity(PsySession ps) {
		if(ps == null) return null;
		PsySessionEntity pse = new PsySessionEntity();
		if(ps.getClientId() != null )
			pse.setClientId(ps.getClientId());
		pse.setSessionDate(dateResolver.toLocalDate(ps.getSessionDate()));
		pse.setFollowupDate(dateResolver.toLocalDate(ps.getFollowupDate()));
		//pse.setSessionRecordEntity(toSessionRecordEntity(ps, pse));
		
		return pse;
	}
	
	public PsySessionEntity updateEntity(PsySessionEntity pse, PsySession dto) {
		if(pse == null || dto == null) return null;
		if(pse.getSessionRecordEntity() == null) return pse;
		
		SessionRecordEntity sreToUpdate = pse.getSessionRecordEntity();
		
		if(sreToUpdate == null) return pse;
		
		if(utils.changed(sreToUpdate.getFeedback(), dto.getFeedback())) {
			sreToUpdate.setFeedback(dto.getFeedback());
		}
		if(utils.changed(sreToUpdate.getImpression(), dto.getImpression())) {
			sreToUpdate.setImpression(dto.getImpression());
		}
		sreToUpdate.setUpdatedOn(LocalDateTime.now());
		pse.setUpdatedOn(LocalDateTime.now());
		return pse;
	}
	
	
	public SessionRecordEntity toSessionRecordEntity(PsySession ps, PsySessionEntity pse) {
		if(pse == null || ps == null) return null;
		SessionRecordEntity sre = new SessionRecordEntity();
		sre.setSessionId(pse.getSessionId());
		sre.setFeedback(ps.getFeedback());
		sre.setSessionEntity(pse);
		sre.setImpression(ps.getImpression());
		sre.setCreatedOn(LocalDateTime.now());
		return sre;
	}
	
	public TreatmentDataEntity toEntity(TreatmentData td) {
		if( td == null) return null;
		TreatmentDataEntity tde = new TreatmentDataEntity();
		tde.setCasehistory(td.getCasehistory());
		if(td.getDegree() != null && StringUtils.isNumeric(td.getDegree()))
			tde.setDegree(Integer.valueOf(td.getDegree()));
		tde.setDiagnosis(td.getDiagnosis());
		tde.setDiffDiagnosis(td.getDiffDiagnosis());
		tde.setDuration(td.getDuration());
		tde.setFormulation(td.getFormulation());
		tde.setLabTesting(td.getLabTesting());
		tde.setMentalComp(td.getMentalComp());
		tde.setOnsetDate(dateResolver.toLocalDate(td.getOnsetDate()));
		tde.setPhysicalComp(td.getPhysicalComp());
		tde.setPsyEvaluation(td.getPsyEvaluation());
		if(td.getSessionId() != null && StringUtils.isNumeric(td.getSessionId()))
			tde.setSessionId(Integer.valueOf(td.getSessionId()));
		if(td.getClientId() != null && StringUtils.isNumeric(td.getClientId()))
			tde.setClientId(Integer.valueOf(td.getClientId()));
		tde.setTherapyApplied(td.getTherapyApplied());
		tde.setClientDocLink(td.getClientDocLink());
		return tde;
	}
	
	public TreatmentData fromEntity(TreatmentDataEntity tde) {
		if(tde == null) return null;
		
		TreatmentData td = new TreatmentData();
		
		td.setCasehistory(tde.getCasehistory());
		td.setClientDocLink(tde.getClientDocLink());
		td.setClientId(tde.getClientId().toString());
		td.setCreatedOn(tde.getCreatedOn());
		td.setDegree(tde.getDegree().toString());
		td.setDiagnosis(tde.getDiagnosis());
		td.setDiffDiagnosis(tde.getDiffDiagnosis());
		td.setDuration(tde.getDuration());
		td.setFormulation(tde.getFormulation());
		td.setLabTesting(tde.getLabTesting());
		td.setMentalComp(tde.getMentalComp());
		td.setOnsetDate(dateResolver.toStringDate(tde.getOnsetDate()));
		td.setPhysicalComp(tde.getPhysicalComp());
		td.setPsyEvaluation(tde.getPsyEvaluation());
		td.setSessionId(tde.getSessionId().toString());
		td.setTherapyApplied(tde.getTherapyApplied());
		td.setTreatmentId(td.getTreatmentId());
		td.setUpdatedOn(tde.getUpdatedOn());
		return td;
	}
	
	public TreatmentDataEntity updateEntity(TreatmentDataEntity tdse, TreatmentData dto) {
		if(tdse == null || dto == null) return null;
		
		if(utils.changed(tdse.getCasehistory(), dto.getCasehistory())) {
			tdse.setCasehistory(dto.getCasehistory());
		}
		if(dto.getDegree() != null && utils.changed(tdse.getDegree(), Integer.valueOf(dto.getDegree()))) {
			tdse.setDegree(Integer.valueOf(dto.getDegree()));
		}
		if(utils.changed(tdse.getDiagnosis(), dto.getDiagnosis())) {
			tdse.setDiagnosis(dto.getDiagnosis());
		}
		if(utils.changed(tdse.getDiffDiagnosis(), dto.getDiffDiagnosis())) {
			tdse.setDiffDiagnosis(dto.getDiffDiagnosis());
		}
		if(utils.changed(tdse.getDuration(), dto.getDuration())) {
			tdse.setDuration(dto.getDuration());
		}
		if(utils.changed(tdse.getFormulation(), dto.getFormulation())) {
			tdse.setFormulation(dto.getFormulation());
		}
		if(utils.changed(tdse.getLabTesting(), dto.getLabTesting())) {
			tdse.setLabTesting(dto.getLabTesting());
		}
		if(utils.changed(tdse.getMentalComp(), dto.getMentalComp())) {
			tdse.setMentalComp(dto.getMentalComp());
		}
		if(utils.changed(dateResolver.toStringDate(tdse.getOnsetDate()), dto.getOnsetDate())) {
			tdse.setOnsetDate(dateResolver.toLocalDate(dto.getOnsetDate()));
		}
		if(utils.changed(tdse.getPhysicalComp(), dto.getPhysicalComp())) {
			tdse.setPhysicalComp(dto.getPhysicalComp());
		}
		if(utils.changed(tdse.getPsyEvaluation(), dto.getPsyEvaluation())) {
			tdse.setPsyEvaluation(dto.getPsyEvaluation());
		}
		if(utils.changed(tdse.getTherapyApplied(), dto.getTherapyApplied())) {
			tdse.setTherapyApplied(dto.getTherapyApplied());
		}
		if(utils.changed(tdse.getClientDocLink(), dto.getClientDocLink())) {
			tdse.setClientDocLink(dto.getClientDocLink());
		}
		
		tdse.setUpdatedOn(LocalDateTime.now());
		return tdse;
	}
	
}
