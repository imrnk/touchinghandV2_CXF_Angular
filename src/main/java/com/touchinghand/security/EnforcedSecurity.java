package com.touchinghand.security;

import javax.annotation.security.RolesAllowed;

@RolesAllowed("ADMIN")
public interface EnforcedSecurity {

}
