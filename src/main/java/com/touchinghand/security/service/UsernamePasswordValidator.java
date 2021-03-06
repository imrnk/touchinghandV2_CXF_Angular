package com.touchinghand.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import com.touchinghand.common.exception.AuthenticationException;
import com.touchinghand.security.Util;
import com.touchinghand.security.entity.User;

@RequestScope
@Component
public class UsernamePasswordValidator {

	@Autowired
    private UserService userService;

	@Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Validate username and password.
     *
     * @param username
     * @param password
     * @return
     */
    public User validateCredentials(String username, String password) {

        User user = userService.findUserByUserName(username);
        
        if( user == null) {
        	throw new AuthenticationException("No such user exists");
        }
        

        if (!user.isActive()) {
            // User is not active
            throw new AuthenticationException("The user is inactive.");
        }

        if (!passwordEncoder.checkPassword(Util.decodeBase64(password), user.getPassword())) {
            // Invalid password
            throw new AuthenticationException("Incorrect password");
        }

        return user;
    }
}
