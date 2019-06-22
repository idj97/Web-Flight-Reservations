package security;

import java.io.IOException;

import javax.annotation.Priority;
import javax.servlet.ServletContext;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;

import model.DataContext;
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
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter	{
	
	@Context
	ServletContext ctx;
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		String authHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
		
		if (checkAuthHeader(authHeader)) {
			DataContext dctx = (DataContext)ctx.getAttribute("data");
			String token = authHeader.substring(7);
			if (dctx.getActiveTokens().containsKey(token)) {
				User u = dctx.getActiveTokens().get(token);
				if (u.isBlocked())
					requestContext.abortWith(Response.status(Response.Status.FORBIDDEN).build());
				
				SecurityContext sctx = requestContext.getSecurityContext();
				RequestSecurityContext rsctx = new RequestSecurityContext(new LoggedUser(u, token), sctx.isSecure());
				requestContext.setSecurityContext(rsctx);
				return;
			}
		}
		
		requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
	}
	
	private boolean checkAuthHeader(String header) {
		if (header == null)
			return false;
		
		if (!header.startsWith("Bearer "))
			return false;
		
		if (header.length() < 8)
			return false;
			
		return true;
	}
	
}
