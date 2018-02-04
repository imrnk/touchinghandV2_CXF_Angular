package com.touchinghand.service.client;

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
import org.springframework.web.context.annotation.RequestScope;

import com.touchinghand.dto.Client;
import com.touchinghand.entity.client.ClientEntity;
import com.touchinghand.service.util.ClientMapper;

@RequestScope
@Component
public class PsyClientServiceImpl implements PsyClientService {

	private static final Logger LOGGER = Logger.getLogger(PsyClientServiceImpl.class.getName());

	@Autowired
	EntityManager em;

	@Autowired
	private ClientMapper mapper;

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
	public Client findClientByName(String name) {
		LOGGER.info("Inside findClientByName ..");
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<ClientEntity> cq = cb.createQuery(ClientEntity.class);
		Root<ClientEntity> from = cq.from(ClientEntity.class);
		cq.where(cb.equal(from.get("clientName"), name));
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
		ClientEntity clientEntity = mapper.toEntity(client);
		if (clientEntity == null)
			return false;
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
	
	

}
