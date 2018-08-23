package com.touchinghand.security.api;

import java.security.Principal;

import javax.ws.rs.core.SecurityContext;

import com.touchinghand.security.domain.Authority;

/**
 * {@link SecurityContext} implementation for token-based authentication.
 *
 */
public class TokenBasedSecurityContext implements SecurityContext {

    private AuthenticatedUserDetails authenticatedUserDetails;
    private AuthenticationTokenDetails authenticationTokenDetails;
    private final boolean secure;

    public TokenBasedSecurityContext(AuthenticatedUserDetails authenticatedUserDetails, AuthenticationTokenDetails authenticationTokenDetails, boolean secure) {
        this.authenticatedUserDetails = authenticatedUserDetails;
        this.authenticationTokenDetails = authenticationTokenDetails;
        this.secure = secure;
    }

    @Override
    public Principal getUserPrincipal() {
        return authenticatedUserDetails;
    }

    @Override
    public boolean isUserInRole(String s) {
        return authenticatedUserDetails.getAuthorities().contains(Authority.valueOf(s));
    }

    @Override
    public boolean isSecure() {
        return secure;
    }

    @Override
    public String getAuthenticationScheme() {
        return "Bearer";
    }

    public AuthenticationTokenDetails getAuthenticationTokenDetails() {
        return authenticationTokenDetails;
    }
}