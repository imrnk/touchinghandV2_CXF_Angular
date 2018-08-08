package com.touchinghand.service.client;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.annotation.RequestScope;

import com.touchinghand.common.CommonUtils;
import com.touchinghand.common.DateResolver;
import com.touchinghand.dto.Client;
import com.touchinghand.dto.ClientMse;
import com.touchinghand.entity.client.ClientEntity;
import com.touchinghand.entity.client.ClientMseEntity;
import com.touchinghand.service.util.ClientMapper;
import com.touchinghand.service.util.ClientMseMapper;

@RequestScope
@Component
public class PsyClientServiceImpl implements PsyClientService {

	private static final Logger LOGGER = Logger.getLogger(PsyClientServiceImpl.class.getName());

	@PersistenceContext
	EntityManager em;

	@Autowired
	private ClientMapper mapper;
	
	@Autowired
	private ClientMseMapper mseMapper;
	
	@Autowired
	private DateResolver dateResolver;

	@Override
	@Transactional
	public List<Client> findActiveClients() {
		LOGGER.info("Inside findActiveClients ..");
		List<ClientEntity> activeClients = activeClientEntities();

		return mapper.fromEntities(activeClients);
	}

	private List<ClientEntity> activeClientEntities() {
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<ClientEntity> cq = cb.createQuery(ClientEntity.class);
		Root<ClientEntity> from = cq.from(ClientEntity.class);
		cq.where(cb.equal(from.get("status"), "Y"));
		List<ClientEntity> activeClients = em.createQuery(cq).getResultList();
		return activeClients;
	}
	
	@Override
	@Transactional
	public List<Client> findActiveClientsWithNoSession() {

		List<ClientEntity> activeClientsWithNoSession = activeClientEntities()
				.stream()
				.filter(activeClientEntity -> CollectionUtils.isEmpty(activeClientEntity.getSessionEntities()))
				.collect(Collectors.toList());
		
		return mapper.fromEntities(activeClientsWithNoSession);
	
	}

	@Override
	@Transactional
	public List<Client> findActiveClientsCrossedFollowupDate() {
		
		//Client entity has a follow up date for which there is
		//no session record with session date equal to the follow up date
		//on the date of querying where now >= follow up date
		LOGGER.info("Inside findActiveClientsCrossedFollowupDate ..");
		LocalDate today = LocalDate.now();
		//This will work if the client has atleast one session. 
		//BUt in case of new client without any session we need not check 
		//session date, rather should only check follow up date
		//which is correct since now we are synching up the follow up date in client
		// and session table
		/*List<ClientEntity> clientEntities = activeClientEntities()
		 .stream()
			.filter(activeClientEntity -> !CollectionUtils.isEmpty(activeClientEntity.getSessionEntities())) // who has no session
			.filter( ac -> (today.isEqual(ac.getFollowupDate()) || today.isAfter(ac.getFollowupDate()))) // who has on or before follow up date
			.filter(filteredClient -> filteredClient.getSessionEntities().stream() 
					.noneMatch(session -> session.getSessionDate().isEqual(filteredClient.getFollowupDate())) // no session date on follow up date 
					).collect(Collectors.toList());*/
		
		List<ClientEntity> clientEntities = activeClientEntities()
				 .stream()
					.filter( ac -> (today.isEqual(ac.getFollowupDate()) || today.isAfter(ac.getFollowupDate()))) // who has on or before follow up date
					.collect(Collectors.toList());
		
		return mapper.fromEntities(clientEntities);
		
	}
	
	@Override
	@Transactional
	public List<Client> findAllClients() {
		LOGGER.info("Inside findAllClients ..");
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<ClientEntity> cq = cb.createQuery(ClientEntity.class);
		List<ClientEntity> allClients = em.createQuery(cq).getResultList();
		
		return mapper.fromEntities(allClients);
	}	

	@Override
	@Transactional
	public Client findClientById(int id) {
		LOGGER.info("Inside findClientById ..");
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<ClientEntity> cq = cb.createQuery(ClientEntity.class);
		Root<ClientEntity> from = cq.from(ClientEntity.class);
		cq.where(cb.equal(from.get("clientId"), id));
		ClientEntity result = null;
		try {
			result = em.createQuery(cq).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}

		return mapper.fromEntity(result);
	}

	@Override
	@Transactional
	public List<Client> findClientByName(String fname, String lname) {
		LOGGER.info("Inside findClientByName ..");
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<ClientEntity> cq = cb.createQuery(ClientEntity.class);
		Root<ClientEntity> from = cq.from(ClientEntity.class);
		Predicate fnamePred = cb.like(from.get("firstName"), "%"+fname+"%"); //cb.equal(from.get("firstName"), fname);
		Predicate lnamePred = cb.like(from.get("lastName"), "%"+lname+"%");
		
		if(StringUtils.isNotEmpty(fname) && StringUtils.isNotEmpty(lname)) {
			cq.where(cb.and(fnamePred, lnamePred));
		} else {
			if(StringUtils.isNotEmpty(fname))
				cq.where(fnamePred);
			else if(StringUtils.isNotEmpty(lname))
				cq.where(lnamePred);
		}
		List<ClientEntity> results = null;
		results = em.createQuery(cq).getResultList();

		return mapper.fromEntities(results);
	}


	@Override
	@Transactional
	public boolean addClient(Client client) {
		LOGGER.info("Inside addClient ..");
		if (client == null) return false;
		ClientEntity clientEntity = mapper.toEntity(client);
		//EntityTransaction tx = em.getTransaction();
		em.persist(clientEntity);
		return true;
	}

	@Override
	@Transactional
	public boolean updateClient(int clientId, Client client) {
		LOGGER.info("Inside updateClient ..");
		
		ClientEntity clientEntityFromDb = em.find(ClientEntity.class, clientId);
		if(clientEntityFromDb == null) {
			throw new RuntimeException("Could not find client by id: " + clientId);
		}
		ClientEntity updatedEntity = mapper.toEntity(clientEntityFromDb, client);
		//EntityTransaction tx = em.getTransaction();
		em.merge(updatedEntity);
		return true;
	}


	@Override
	@Transactional
	public boolean addClientMse(int clientId, ClientMse clientMse) {
		LOGGER.info("Inside addClientMse ..");
		
		ClientEntity clientEntityFromDb = em.find(ClientEntity.class, clientId);
		if(clientEntityFromDb == null) {
			throw new RuntimeException("Could not find client by id: " + clientId);
		}
		if(clientMse == null) return false;
		LOGGER.info(CommonUtils.toJson(clientMse));
		ClientMseEntity mseEntity = mseMapper.toEntity(clientMse);
		//EntityTransaction tx = em.getTransaction();

		//TODO: em.persist(mseEntity);
		
		return true;
	}

	
	@Override
	@Transactional
	public boolean updateClientMse(int clientMseId, ClientMse clientMse) {
		LOGGER.info("Inside updateClientMse ..");
		
		ClientMseEntity cmseEntityDb = em.find(ClientMseEntity.class, clientMseId);
		if(cmseEntityDb == null) {
			throw new RuntimeException("Client MSE record not found for id: " + clientMseId);
		}
		
		ClientMseEntity updatedEntity = mseMapper.toEntity(cmseEntityDb, clientMse);
		updatedEntity.setUpdatedOn(LocalDateTime.now());
		//EntityTransaction tx = em.getTransaction();
		em.merge(updatedEntity);
		return true;
	}
	

	@Override
	@Transactional
	public ClientMse getClientMse(int clientId) {
		LOGGER.info("Inside getClientMse ..");
		LOGGER.info("Inside findClientById ..");
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<ClientMseEntity> cq = cb.createQuery(ClientMseEntity.class);
		Root<ClientMseEntity> from = cq.from(ClientMseEntity.class);
		cq.where(cb.equal(from.get("clientId"), clientId));
		ClientMseEntity result = null;
		try {
			result = em.createQuery(cq).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
		return mseMapper.fromEntity(result);
	}
	
	@Override
	@Transactional
	public List<Client> getUpcomingSessions(String start, String end) {
		LOGGER.info("Inside getUpcomingSessions ");
		if(StringUtils.isEmpty(start) || StringUtils.isEmpty(end)) throw new RuntimeException("Dates are required to search");
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<ClientEntity> cq = cb.createQuery(ClientEntity.class);
		
		Root<ClientEntity> from = cq.from(ClientEntity.class);
		cq.where(cb.between(from.get("followupDate"), dateResolver.toLocalDate(start), dateResolver.toLocalDate(end)));
		List<ClientEntity> results = new ArrayList<>();
		
		results = em.createQuery(cq).getResultList();
		results.forEach(r -> em.refresh(r));
		
		return mapper.fromEntities(results);
	}

}
