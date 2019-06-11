package controller;

import javax.servlet.ServletContext;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import common.RandomStringGenerator;
import model.DataContext;
import model.User;
import security.AuthRole;
import security.Secured;

@Path("/auth")
public class AuthenticationController {
	
	@Context
	ServletContext ctx;
	
	@Context
	SecurityContext sctx;
	
	@POST
	@Path("/{username}/{password}")
	@Produces(MediaType.TEXT_PLAIN)
	public String authenticate(@PathParam("username") String username, @PathParam("password") String password) {
		for (User u : getDataContext().getUsers()) {
			if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
				String token = RandomStringGenerator.get();
				u.setToken(token);
				return token;
			}
		}
		return "USER NOT FOUND.";
	}
	
	
	@POST
	@Path("/register/{username}/{password}/{role}")
	@Produces(MediaType.APPLICATION_JSON)
	public User register(@PathParam("username") String username, 
			@PathParam("password") String password,
			@PathParam("role") AuthRole role) {
		
		User u = new User(username, password, role);
		getDataContext().getUsers().add(u);
		return u;
	}
	
	@POST
	@Secured
	@Path("/logout")
	public String logout() {
		User u = getLoggedUser();
		u.setToken(null);
		return "Farewell :(";
	}
	
	
	private DataContext getDataContext() {
		return (DataContext) ctx.getAttribute("data");
	}
	
	private User getLoggedUser() {
		String username = sctx.getUserPrincipal().getName();
		for (User u : getDataContext().getUsers()) {
			if (u.getUsername().equals(username))
				return u;
		}
		return null;
	}
}
