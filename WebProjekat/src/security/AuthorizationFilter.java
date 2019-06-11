package security;

import java.io.IOException;

import javax.annotation.Priority;
import javax.servlet.ServletContext;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;

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
	ServletContext ctx;
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		
	}
	
}	
