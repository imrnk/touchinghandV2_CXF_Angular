package com.touchinghand.service.session;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.touchinghand.dto.PsySession;
import com.touchinghand.dto.TreatmentData;
import com.touchinghand.entity.session.PsySessionEntity;
import com.touchinghand.entity.session.SessionRecordEntity;
import com.touchinghand.entity.session.TreatmentDataEntity;
import com.touchinghand.service.util.PsySessionMapper;

@Component
public class PsySessionServiceImpl implements PsySessionService {


	private static final Logger LOGGER = Logger.getLogger(PsySessionServiceImpl.class.getName());

	@Autowired
	private EntityManager em;
	
	@Autowired
	private PsySessionMapper sessionMapper;

	@Override
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
	public List<PsySession> getSessionBetween(LocalDate start, LocalDate end) {
		LOGGER.info("Inside getSessionBetween ");
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<PsySessionEntity> cq = cb.createQuery(PsySessionEntity.class);
		Root<PsySessionEntity> from = cq.from(PsySessionEntity.class);
		
		cq.where(cb.between(from.get("sessionDate"), start, end));
		List<PsySessionEntity> results = new ArrayList<>();
		
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
	public boolean createSession(PsySession s) {
		LOGGER.info("Inside createSession ");
		if ( s == null) return false;
		PsySessionEntity pse = sessionMapper.toEntity(s);
		pse.setCreatedOn(LocalDateTime.now());
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.persist(pse);
			tx.commit();
		} catch (RuntimeException e) {
			tx.rollback();
			throw new RuntimeException(e.getMessage());

		}
		em.refresh(pse);
		return createSessionRecord(s, pse);
	}
	
	private boolean createSessionRecord(PsySession s, PsySessionEntity pse) {
		LOGGER.info("Inside createSessionRecord ");
		SessionRecordEntity sre = sessionMapper.toSessionRecordEntity(s, pse);
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.persist(sre);
			tx.commit();
		} catch (RuntimeException e) {
			tx.rollback();
			throw new RuntimeException(e.getMessage());

		}
		return true;
		
	}

	@Override
	public boolean updateSession(PsySession s) {
		LOGGER.info("Inside updateSession ");
		if ( s == null) return false;
		PsySessionEntity psyEntityFromDB = em.find(PsySessionEntity.class, Integer.valueOf(s.getSessionId()));
		
		sessionMapper.updateEntity(psyEntityFromDB, s);
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.merge(psyEntityFromDB);
			tx.commit();
		} catch (RuntimeException e) {
			tx.rollback();
			throw new RuntimeException(e.getMessage());

		}
		return true;
	}

	@Override
	public boolean createTreatmentData(TreatmentData s) {
		LOGGER.info("Inside createTreatmentData ");
		if ( s == null) return false;
		TreatmentDataEntity tde = sessionMapper.toEntity(s);
		tde.setCreatedOn(LocalDateTime.now());
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.persist(tde);
			tx.commit();
		} catch (RuntimeException e) {
			tx.rollback();
			throw new RuntimeException(e.getMessage());

		}
		return true;
	}

	@Override
	public boolean updateTreatmentData(TreatmentData s) {
		LOGGER.info("Inside updateTreatmentData ");
		if ( s == null) return false;
		TreatmentDataEntity tdeDB = em.find(TreatmentDataEntity.class, s.getTreatmentId());
		
		sessionMapper.updateEntity(tdeDB, s);
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.merge(tdeDB);
			tx.commit();
		} catch (RuntimeException e) {
			tx.rollback();
			throw new RuntimeException(e.getMessage());

		}
		return true;
	}

	

}
