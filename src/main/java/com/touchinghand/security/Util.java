package com.touchinghand.security;

import java.util.Base64;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import org.springframework.stereotype.Component;

import com.touchinghand.security.api.model.RegistrationUser;
import com.touchinghand.security.api.model.UserCredentials;
import com.touchinghand.security.domain.Authority;
import com.touchinghand.security.entity.User;
import com.touchinghand.security.entity.UserRole;

@Component
public final class Util {
	private static final Logger LOGGER = Logger.getLogger(Util.class.getName());
	
	public static Set<Authority> fromRoles(Set<UserRole> roles) {
		Set<Authority> authorities = new HashSet<>();
		
		for(UserRole r : roles) {
			LOGGER.info(r.getUserRole());
			if(r.getUserRole().equals(Authority.ADMIN.name())) {
    			authorities.add(Authority.ADMIN);
    		}
    		return authorities;
		}
		
		return authorities;
	}
	
	public static User fromModel(RegistrationUser regUser, Authority roleEnum) {
		UserRole role = new UserRole();
		role.setUserName(regUser.getUsername());
		role.setUserRole(roleEnum.name());
		return fromModel(regUser, role);
	}
	
	public static UserCredentials fromRegUser(RegistrationUser regUser) {
		UserCredentials cred = new UserCredentials();
		cred.setUsername(regUser.getUsername());
		cred.setPassword(regUser.getPassword());
		return cred;
	}
	
	public static UserCredentials fromUser(User user) {
		UserCredentials cred = new UserCredentials();
		cred.setUsername(user.getUsername());
		cred.setPassword(user.getPassword());
		return cred;
	}
	
	public static User fromModel(RegistrationUser regUser, UserRole role) {
		User user = new User();
		user.addRole(role);
		
		user.setUsername(regUser.getUsername());
		user.setPassword(Util.decodeBase64(regUser.getPassword()));
		user.setFirstName(regUser.getFirstName());
		user.setLastName(regUser.getLastName());
		user.setActive(true);
		
		return user;
	}
	
	public static String decodeBase64 (String s) {
		return new String(Base64.getDecoder().decode(s));
	}
	
	public static String encodeBase64 (String s) {
		return new String(Base64.getEncoder().encodeToString(s.getBytes()));
	}
}
