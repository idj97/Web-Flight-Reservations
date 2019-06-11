package controller;

import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import model.DataContext;
import model.Person;
import model.User;
import security.Secured;

@Path("/products")
public class PersonController {
	
	@Context
	ServletContext ctx;
	
	@Context
	SecurityContext sctx;
	 
	
	@GET
	@Path("/getOne")
	@Produces(MediaType.APPLICATION_JSON)
	public Person getProduct() {
		System.out.println("GET ONE METHOD CALLED");
		return new Person("marko", "markovic", 35);
	}
	
	
	@POST
	@Secured
	@Path("/add")
	public void add() {
		DataContext dctx = (DataContext) ctx.getAttribute("data");
		dctx.getPersons().add(new Person("mike","mikeic", 12));
		User u = getLoggedUser();
		System.out.println("USER " + u.getUsername() + " CREATED NEW PERSON.");
	}
	
	
	@GET
	@Path("/getAll")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Person> getAllProducts() {
		System.out.println("GET ALL METHOD CALLED");
		return ((DataContext)ctx.getAttribute("data")).getPersons();
	}
	
	
	private User getLoggedUser() {
		String username = sctx.getUserPrincipal().getName();
		for (User u : getDataContext().getUsers()) {
			if (u.getUsername().equals(username))
				return u;
		}
		return null;
	}
	
	
	private DataContext getDataContext() {
		return (DataContext) ctx.getAttribute("data");
	}
	
}
