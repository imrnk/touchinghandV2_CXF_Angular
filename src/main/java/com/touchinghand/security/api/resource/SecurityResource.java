package com.touchinghand.security.api.resource;

import java.security.Principal;
import java.util.HashSet;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.touchinghand.security.Util;
import com.touchinghand.security.api.AuthenticationTokenDetails;
import com.touchinghand.security.api.TokenBasedSecurityContext;
import com.touchinghand.security.api.model.AuthenticationToken;
import com.touchinghand.security.api.model.QueryUserResult;
import com.touchinghand.security.api.model.RegistrationUser;
import com.touchinghand.security.api.model.UserCredentials;
import com.touchinghand.security.domain.Authority;
import com.touchinghand.security.entity.User;
import com.touchinghand.security.service.AuthenticationTokenService;
import com.touchinghand.security.service.UserService;
import com.touchinghand.security.service.UsernamePasswordValidator;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

//@EnforcedSecurity
@RolesAllowed("ADMIN")
@Api(value = "/auth", tags = "security")
@Path("/auth")
public class SecurityResource {
	
	@Context
    private SecurityContext securityContext;

    @Autowired
    private UserService userService;
    
    @Autowired
    private UsernamePasswordValidator usernamePasswordValidator;

    @Autowired
    private AuthenticationTokenService authenticationTokenService;
    

    @ApiOperation(value = "Get Authenticated user", 
    		notes = "Get Authenticated user", 
    		response = QueryUserResult.class)
    @GET
    @Path("/me")
    @Produces(MediaType.APPLICATION_JSON)
    @PermitAll
    public Response getAuthenticatedUser() {

        Principal principal = securityContext.getUserPrincipal();

        if (principal == null) {
            QueryUserResult queryUserResult = new QueryUserResult();
            queryUserResult.setUsername("anonymous");
            queryUserResult.setAuthorities(new HashSet<>());
            return Response.ok(queryUserResult).build();
        }

        User user = userService.findUserByUserName(principal.getName()).get();
        QueryUserResult queryUserResult = toQueryUserResult(user);
        return Response.ok(queryUserResult).build();
    }

    /**
     * Maps a {@link User} instance to a {@link QueryUserResult} instance.
     *
     * @param user
     * @return
     */
    private QueryUserResult toQueryUserResult(User user) {
        QueryUserResult queryUserResult = new QueryUserResult();
        queryUserResult.setId(String.valueOf(user.getUserId()));
        queryUserResult.setUsername(user.getUsername());
        queryUserResult.setFirstName(user.getFirstName());
        queryUserResult.setLastName(user.getLastName());
        queryUserResult.setAuthorities(Util.fromRoles(user.getRoles()));
        queryUserResult.setActive(user.isActive());
        return queryUserResult;
    }

    /**
     * Validate user credentials and issue a token for the user.
     *
     * @param credentials
     * @return
     */
    @ApiOperation(value = "Authenticate user", 
    		notes = "Authenticate user", 
    		response = AuthenticationToken.class)
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @PermitAll
    public Response authenticate(UserCredentials credentials) {

        User user = usernamePasswordValidator.validateCredentials(credentials.getUsername(), credentials.getPassword());
        String token = authenticationTokenService.issueToken(user.getUsername(), Util.fromRoles(user.getRoles()));
        AuthenticationToken authenticationToken = new AuthenticationToken();
        authenticationToken.setToken(token);
        return Response.ok(authenticationToken).build();
    }

    /**
     * Refresh the authentication token for the current user.
     *
     * @return
     */
    @ApiOperation(value = "refresh auth token", 
    		notes = "refresh auth token", 
    		response = AuthenticationToken.class)
    @POST
    @Path("/refresh")
    @Produces(MediaType.APPLICATION_JSON)
    public Response refresh() {

        AuthenticationTokenDetails tokenDetails =
                ((TokenBasedSecurityContext) securityContext).getAuthenticationTokenDetails();
        String token = authenticationTokenService.refreshToken(tokenDetails);

        AuthenticationToken authenticationToken = new AuthenticationToken();
        authenticationToken.setToken(token);
        return Response.ok(authenticationToken).build();
    }

    @ApiOperation(value = "Register user", 
    		notes = "Register user", 
    		response = AuthenticationToken.class)
    @POST
    @Path("/register")
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(RegistrationUser regUser) {
    	
    	User user = Util.fromModel(regUser, Authority.ADMIN);
    	
    	userService.registerUser(user);
    	
        return authenticate(Util.fromRegUser(regUser));
    }

}
