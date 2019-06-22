package security;

import java.io.IOException;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;

import javax.annotation.Priority;
import javax.servlet.ServletContext;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;

import model.User;

/*
 * Web resources:
 *  1) https://stackoverflow.com/questions/26777083/best-practice-for-rest-token-based-authentication-with-jax-rs-and-jersey
 *  2) https://blog.dejavu.sk/binding-jax-rs-providers-to-resource-methods/
 *  3) https://blog.dejavu.sk/registering-resources-and-providers-in-jersey-2/
 *  4) https://github.com/cassiomolin/jersey-jwt
 */

@Secured
@Provider
@Priority(Priorities.AUTHORIZATION)
public class AuthorizationFilter implements ContainerRequestFilter {
	
	@Context
	private ServletContext ctx;
	
	@Context
	private ResourceInfo resourceInfo;
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		Method resourceMethod = resourceInfo.getResourceMethod();
		AuthRole methodRole = extractRoles(resourceMethod);
		User user = getLoggedUser(requestContext.getSecurityContext());
		
		if (methodRole == AuthRole.ADMIN && user.getRole() != AuthRole.ADMIN)
			requestContext.abortWith(Response.status(Response.Status.FORBIDDEN).build());
	}
	
	
	private AuthRole extractRoles(AnnotatedElement method) {
		// moze da vrati null ako putanja resursa koju zelimo da pogodimo nije prepoznata
		if (method == null)
			return AuthRole.USER;
		else {
			Secured secured = method.getAnnotation(Secured.class);
			if (secured == null) 
				return AuthRole.USER;
			else
				return secured.role();
		}
	}
	
	private User getLoggedUser(SecurityContext sctx) {
		return ((LoggedUser) sctx.getUserPrincipal()).getUser();
	}
	
}	
