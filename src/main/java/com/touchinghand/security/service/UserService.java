package com.touchinghand.security.service;

import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import com.touchinghand.common.exception.InvalidUsernameException;
import com.touchinghand.security.entity.User;

@RequestScope
@Component
public class UserService {

	private static final Logger LOGGER = Logger.getLogger(UserService.class.getName());

	@PersistenceContext
	EntityManager em;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Transactional
	public User findUserByUserName(String userName) {

		try {
			LOGGER.info("Inside findUserByUserName ..");
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<User> cq = cb.createQuery(User.class);
			Root<User> from = cq.from(User.class);
			cq.where(cb.equal(from.get("username"), userName));
			return em.createQuery(cq).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Transactional
	public boolean registerUser(User user) {
		LOGGER.info("Inside registerUser ..");

		if (user == null)
			return false;

		if (findUserByUserName(user.getUsername()) != null) {
			throw new InvalidUsernameException("An user with " + user.getUsername() + " is already registered");
		}

		String unhashedPassword = user.getPassword();
		user.setPassword(passwordEncoder.hashPassword(unhashedPassword));

		em.persist(user);
		return true;
	}
}
