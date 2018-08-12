package com.touchinghand.service.session;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.touchinghand.common.DateResolver;
import com.touchinghand.dto.Client;
import com.touchinghand.dto.PsySession;
import com.touchinghand.dto.TreatmentData;
import com.touchinghand.entity.session.PsySessionEntity;
import com.touchinghand.entity.session.SessionRecordEntity;
import com.touchinghand.entity.session.TreatmentDataEntity;
import com.touchinghand.service.client.PsyClientService;
import com.touchinghand.service.util.PsySessionMapper;

@Component
public class PsySessionServiceImpl implements PsySessionService {


	private static final Logger LOGGER = Logger.getLogger(PsySessionServiceImpl.class.getName());

	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private PsySessionMapper sessionMapper;
	
	@Autowired
	private DateResolver dateResolver;
	
	@Autowired
	private PsyClientService clientService;

	@Override
	@Transactional
	public List<PsySession> getSessionOfClient(int clientId) {
		LOGGER.info("Inside getSessionOfClient ");
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<PsySessionEntity> cq = cb.createQuery(PsySessionEntity.class);
		Root<PsySessionEntity> from = cq.from(PsySessionEntity.class);
		cq.where(cb.equal(from.get("clientId"), clientId));
		List<PsySessionEntity> results = new ArrayList<>();
		
		results = em.createQuery(cq).getResultList();
		results.forEach(r -> em.refresh(r));
		
		return sessionMapper.fromEntities(results);
	}
	

	@Override
	@Transactional
	public TreatmentData getTreatmentData(int clientId) {
		LOGGER.info("Inside getTreatmentData ");
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<TreatmentDataEntity> cq = cb.createQuery(TreatmentDataEntity.class);
		Root<TreatmentDataEntity> from = cq.from(TreatmentDataEntity.class);
		cq.where(cb.equal(from.get("clientId"), clientId));
		
		try {
			TreatmentDataEntity tde = em.createQuery(cq).getSingleResult();
			return sessionMapper.fromEntity(tde);
		} catch (NoResultException e) {
			return null;
		}
		
	}
	
	@Override
	public PsySession getSession(int sessionId) {
		LOGGER.info("Inside getSession ");
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<PsySessionEntity> cq = cb.createQuery(PsySessionEntity.class);
		Root<PsySessionEntity> from = cq.from(PsySessionEntity.class);
		cq.where(cb.equal(from.get("sessionId"), sessionId));
		PsySessionEntity result = null;
		try {
			result = em.createQuery(cq).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
		return sessionMapper.fromEntity(result);
	}

	@Override
	@Transactional
	public List<PsySession> getSessionBetween(String start, String end) {
		LOGGER.info("Inside getSessionBetween ");
		if(StringUtils.isEmpty(start) || StringUtils.isEmpty(end)) throw new RuntimeException("Dates are required to search");
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<PsySessionEntity> cq = cb.createQuery(PsySessionEntity.class);
		
		Root<PsySessionEntity> from = cq.from(PsySessionEntity.class);
		cq.where(cb.between(from.get("sessionDate"), dateResolver.toLocalDate(start), dateResolver.toLocalDate(end)));
		List<PsySessionEntity> results = new ArrayList<>();
		
		results = em.createQuery(cq).getResultList();
		results.forEach(r -> em.refresh(r));
		
		return sessionMapper.fromEntities(results);
	}

	
	@Override
	@Transactional
	public List<PsySession> getSessionOfClientBetween(int clientId, String start, String end) {
		LOGGER.info("Inside getSessionOfClientBetween ");
		if(StringUtils.isEmpty(start) || StringUtils.isEmpty(end)) throw new RuntimeException("Dates are required to search");
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<PsySessionEntity> cq = cb.createQuery(PsySessionEntity.class);
		
		Root<PsySessionEntity> from = cq.from(PsySessionEntity.class);
		cq.where(cb.between(from.get("sessionDate"), dateResolver.toLocalDate(start), dateResolver.toLocalDate(end)));
		List<PsySessionEntity> results = new ArrayList<>();
		
		cq.where(cb.equal(from.get("clientId"), clientId));
		results = em.createQuery(cq).getResultList();
		results.forEach(r -> em.refresh(r));
		
		return sessionMapper.fromEntities(results);
	}

	@Override
	public List<PsySession> getAllSessionFeedbackOfClient(int clientId) {
		//TODO 
		return null;
	}

	@Override
	public List<PsySession> getAllSessionImpressionsOfClient(int clientId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	@Transactional
	public Integer createSession(PsySession s) {
		LOGGER.info("Inside createSession ");
		if ( s == null) return null;
		PsySessionEntity pse = sessionMapper.toEntity(s);
		pse.setCreatedOn(LocalDateTime.now());
		//EntityTransaction tx = em.getTransaction();
		em.persist(pse);
		em.refresh(pse);
		//Update the follow up date in client entity too
		if(StringUtils.isNotBlank(s.getFollowupDate())) {
			Client updatedClient = new Client();
			updatedClient.setFollowupdate(s.getFollowupDate());
			clientService.updateClient(Integer.valueOf(s.getClientId()), updatedClient);
		}
		return createSessionRecord(s, pse)? pse.getSessionId() : null;
	}
	
	private boolean createSessionRecord(PsySession s, PsySessionEntity pse) {
		LOGGER.info("Inside createSessionRecord ");
		SessionRecordEntity sre = sessionMapper.toSessionRecordEntity(s, pse);
//		EntityTransaction tx = em.getTransaction();
		em.persist(sre);
		return true;
		
	}

	@Override
	@Transactional
	public boolean updateSession(PsySession s) {
		LOGGER.info("Inside updateSession ");
		if ( s == null) return false;
		PsySessionEntity psyEntityFromDB = em.find(PsySessionEntity.class, Integer.valueOf(s.getSessionId()));
		
		sessionMapper.updateEntity(psyEntityFromDB, s);
	//	EntityTransaction tx = em.getTransaction();
		em.merge(psyEntityFromDB);
		return true;
	}

	@Override
	@Transactional
	public boolean createTreatmentData(TreatmentData s) {
		LOGGER.info("Inside createTreatmentData ");
		if ( s == null) return false;
		TreatmentDataEntity tde = sessionMapper.toEntity(s);
		tde.setCreatedOn(LocalDateTime.now());
	//	EntityTransaction tx = em.getTransaction();
		em.persist(tde);
		return true;
	}

	@Override
	@Transactional
	public boolean updateTreatmentData(TreatmentData s) {
		LOGGER.info("Inside updateTreatmentData ");
		if ( s == null) return false;
		TreatmentDataEntity tdeDB = em.find(TreatmentDataEntity.class, s.getTreatmentId());
		
		sessionMapper.updateEntity(tdeDB, s);
	//	EntityTransaction tx = em.getTransaction();
		em.merge(tdeDB);
		return true;
	}

}
