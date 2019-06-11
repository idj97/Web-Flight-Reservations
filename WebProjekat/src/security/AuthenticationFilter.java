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

@Secured
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter	{
	
	@Context	
	ServletContext ctx;
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		System.out.println("AUTHENTICATION FILTER");
		String authHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
		
		
		if (checkAuthHeader(authHeader)) {
			DataContext dctx = (DataContext)ctx.getAttribute("data");
			String token = authHeader.substring(7);
			for (User u : dctx.getUsers()) {
				if (u.getToken() != null && u.getToken().equals(token)) {
					SecurityContext sctx = requestContext.getSecurityContext();
					RequestSecurityContext rsctx = new RequestSecurityContext(u, sctx.isSecure());
					requestContext.setSecurityContext(rsctx);
					return;
				}
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
