package com.touchinghand.security;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.touchinghand.security.domain.Authority;
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
}
