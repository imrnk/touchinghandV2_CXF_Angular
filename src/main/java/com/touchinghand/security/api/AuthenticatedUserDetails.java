package com.touchinghand.security.api;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.touchinghand.security.domain.Authority;
import com.touchinghand.security.entity.UserRole;

/**
 *
 */
public final class AuthenticatedUserDetails implements Principal {

    private final String username;
    private final Set<Authority> authorities;

    public AuthenticatedUserDetails(String username, Set<Authority> authorities) {
        this.username = username;
        this.authorities = authorities;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getName() {
        return username;
    }
}