package com.touchinghand.security.api.filter;

import javax.ws.rs.container.DynamicFeature;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.FeatureContext;

//@Provider
//@ApplicationScope
public class AuthorizationAnnotationCheckFeature implements DynamicFeature {
	//private static final Logger LOGGER = Logger.getLogger(AuthorizationAnnotationCheckFeature.class.getName());
	@Override
	public void configure(ResourceInfo resourceInfo, FeatureContext context) {/*
		if(resourceInfo.getResourceMethod().getAnnotation(RolesAllowed.class) != null ) {
			LOGGER.info("Registering ");
            context.register(new AuthorizationFilter(resourceInfo));
        }

	*/}

}
