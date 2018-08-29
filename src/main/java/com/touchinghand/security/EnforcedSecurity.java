package com.touchinghand.security;

import java.lang.annotation.Inherited;

import javax.annotation.security.RolesAllowed;

@Inherited
@RolesAllowed("ADMIN")
public @interface EnforcedSecurity {

}
