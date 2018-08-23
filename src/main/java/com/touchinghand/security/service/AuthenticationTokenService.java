package com.touchinghand.security.service;

import java.time.ZonedDateTime;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

import com.touchinghand.common.exception.AuthenticationTokenRefreshmentException;
import com.touchinghand.security.api.AuthenticationTokenDetails;
import com.touchinghand.security.domain.Authority;

/**
 * Service which provides operations for authentication tokens.
 *
 */
@ApplicationScope
@Component
public class AuthenticationTokenService {

    /**
     * How long the token is valid for (in seconds).
     */
    @Autowired
    @Value("${authentication.jwt.validFor}")
    private Long validFor;

    /**
     * How many times the token can be refreshed.
     */
    @Autowired
    @Value("${authentication.jwt.refreshLimit}")
    private Integer refreshLimit;

    @Autowired
    private AuthenticationTokenIssuer tokenIssuer;

    @Autowired
    private AuthenticationTokenParser tokenParser;

    /**
     * Issue a token for a user with the given authorities.
     *
     * @param username
     * @param authorities
     * @return
     */
    public String issueToken(String username, Set<Authority> authorities) {

        String id = generateTokenIdentifier();
        ZonedDateTime issuedDate = ZonedDateTime.now();
        ZonedDateTime expirationDate = calculateExpirationDate(issuedDate);

        AuthenticationTokenDetails authenticationTokenDetails = new AuthenticationTokenDetails.Builder()
                .withId(id)
                .withUsername(username)
                .withAuthorities(authorities)
                .withIssuedDate(issuedDate)
                .withExpirationDate(expirationDate)
                .withRefreshCount(0)
                .withRefreshLimit(refreshLimit)
                .build();

        return tokenIssuer.issueToken(authenticationTokenDetails);
    }

    /**
     * Parse and validate the token.
     *
     * @param token
     * @return
     */
    public AuthenticationTokenDetails parseToken(String token) {
        return tokenParser.parseToken(token);
    }

    /**
     * Refresh a token.
     *
     * @param currentTokenDetails
     * @return
     */
    public String refreshToken(AuthenticationTokenDetails currentTokenDetails) {

        if (!currentTokenDetails.isEligibleForRefreshment()) {
            throw new AuthenticationTokenRefreshmentException("This token cannot be refreshed");
        }

        ZonedDateTime issuedDate = ZonedDateTime.now();
        ZonedDateTime expirationDate = calculateExpirationDate(issuedDate);

        AuthenticationTokenDetails newTokenDetails = new AuthenticationTokenDetails.Builder()
                .withId(currentTokenDetails.getId()) // Reuse the same id
                .withUsername(currentTokenDetails.getUsername())
                .withAuthorities(currentTokenDetails.getAuthorities())
                .withIssuedDate(issuedDate)
                .withExpirationDate(expirationDate)
                .withRefreshCount(currentTokenDetails.getRefreshCount() + 1)
                .withRefreshLimit(refreshLimit)
                .build();

        return tokenIssuer.issueToken(newTokenDetails);
    }

    /**
     * Calculate the expiration date for a token.
     *
     * @param issuedDate
     * @return
     */
    private ZonedDateTime calculateExpirationDate(ZonedDateTime issuedDate) {
        return issuedDate.plusSeconds(validFor);
    }

    /**
     * Generate a token identifier.
     *
     * @return
     */
    private String generateTokenIdentifier() {
        return UUID.randomUUID().toString();
    }
}