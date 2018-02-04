package com.touchinghand.service.client;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

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

	@Autowired
	EntityManager em;

	@Autowired
	private ClientMapper mapper;
	
	@Autowired
	private ClientMseMapper mseMapper;

	@Override
	public List<Client> findActiveClients() {
		LOGGER.info("Inside findActiveClients ..");
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<ClientEntity> cq = cb.createQuery(ClientEntity.class);
		Root<ClientEntity> from = cq.from(ClientEntity.class);
		cq.where(cb.equal(from.get("status"), "Y"));
		List<ClientEntity> activeClients = em.createQuery(cq).getResultList();

		return mapper.fromEntities(activeClients);
	}
	


	@Override
	public List<Client> findAllClients() {
		LOGGER.info("Inside findAllClients ..");
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<ClientEntity> cq = cb.createQuery(ClientEntity.class);
		List<ClientEntity> allClients = em.createQuery(cq).getResultList();
		
		return mapper.fromEntities(allClients);
	}	

	@Override
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
	public Client findClientByName(String fname, String lname) {
		LOGGER.info("Inside findClientByName ..");
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<ClientEntity> cq = cb.createQuery(ClientEntity.class);
		Root<ClientEntity> from = cq.from(ClientEntity.class);
		if(StringUtils.isNotEmpty(fname))
			cq.where(cb.equal(from.get("firstName"), fname));
		if(StringUtils.isNotEmpty(lname))
			cq.where(cb.equal(from.get("lastName"), lname));
		ClientEntity result = null;
		try {
			result = em.createQuery(cq).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}

		return mapper.fromEntity(result);
	}



	@Override
	public boolean addClient(Client client) {
		LOGGER.info("Inside addClient ..");
		
		if (client == null) return false;
		ClientEntity clientEntity = mapper.toEntity(client);
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.persist(clientEntity);
			tx.commit();
		} catch (RuntimeException e) {
			tx.rollback();
			throw new RuntimeException(e.getMessage());

		}
		return true;
	}

	@Override
	public boolean updateClient(int clientId, Client client) {
		LOGGER.info("Inside updateClient ..");
		
		ClientEntity clientEntityFromDb = em.find(ClientEntity.class, clientId);
		if(clientEntityFromDb == null) {
			throw new RuntimeException("Could not find client by id: " + clientId);
		}
		ClientEntity updatedEntity = mapper.toEntity(clientEntityFromDb, client);
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.merge(updatedEntity);
			tx.commit();
		} catch (RuntimeException e) {
			tx.rollback();
			throw new RuntimeException(e.getMessage());
		}
		return true;
	}


	@Override
	public boolean addClientMse(int clientId, ClientMse clientMse) {
		LOGGER.info("Inside addClientMse ..");
		
		ClientEntity clientEntityFromDb = em.find(ClientEntity.class, clientId);
		if(clientEntityFromDb == null) {
			throw new RuntimeException("Could not find client by id: " + clientId);
		}
		if(clientMse == null) return false;
		ClientMseEntity mseEntity = mseMapper.toEntity(clientMse);
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			em.persist(mseEntity);
			tx.commit();
		} catch (RuntimeException e) {
			tx.rollback();
			throw new RuntimeException(e.getMessage());
		}
		
		return true;
	}

	
	@Override
	public boolean updateClientMse(int clientMseId, ClientMse clientMse) {
		LOGGER.info("Inside updateClientMse ..");
		
		ClientMseEntity cmseEntityDb = em.find(ClientMseEntity.class, clientMseId);
		if(cmseEntityDb == null) {
			throw new RuntimeException("Client MSE record not found for id: " + clientMseId);
		}
		
		ClientMseEntity updatedEntity = mseMapper.toEntity(cmseEntityDb, clientMse);
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.merge(updatedEntity);
			tx.commit();
		} catch (RuntimeException e) {
			tx.rollback();
			throw new RuntimeException(e.getMessage());
		}
		return true;
	}
	

	@Override
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
	

}
