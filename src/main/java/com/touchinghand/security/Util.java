package com.touchinghand.security;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.touchinghand.security.api.model.RegistrationUser;
import com.touchinghand.security.api.model.UserCredentials;
import com.touchinghand.security.domain.Authority;
import com.touchinghand.security.entity.User;
import com.touchinghand.security.entity.UserRole;

@Component
public final class Util {

	
	public static Set<Authority> fromRoles(List<UserRole> roles) {
		Set<Authority> authorities = new HashSet<>();
		roles.stream().map(r -> 
    	{
    		if(r.getUserRole().equals(Authority.ADMIN.name())) {
    			authorities.add(Authority.ADMIN);
    		}
    		return authorities;
    		});
		return authorities;
	}
	
	public static User fromModel(RegistrationUser regUser, Authority roleEnum) {
		UserRole role = new UserRole();
		role.setUserRole(roleEnum.name());
		return fromModel(regUser, role);
	}
	
	public static UserCredentials fromRegUser(RegistrationUser regUser) {
		UserCredentials cred = new UserCredentials();
		cred.setUsername(regUser.getUsername());
		cred.setPassword(regUser.getPassword());
		return cred;
	}
	
	public static User fromModel(RegistrationUser regUser, UserRole role) {
		User user = new User();
		user.addRole(role);
		
		user.setUsername(regUser.getUsername());
		user.setPassword(regUser.getPassword());
		user.setFirstName(regUser.getFirstName());
		user.setLastName(regUser.getLastName());
		user.setActive(true);
		
		return user;
	}
}
