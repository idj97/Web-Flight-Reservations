package controller;

import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import common.ImageHandler;
import model.DataContext;
import model.Person;
import model.User;
import security.AuthRole;
import security.Secured;

@Path("/persons")
public class PersonController {
	
	@Context
	private ServletContext ctx;
	
	@Context
	private SecurityContext sctx;
	 
	
	@GET
	@Path("/getOne")
	@Produces(MediaType.APPLICATION_JSON)
	public Person getPerson() {
		System.out.println("GET ONE METHOD CALLED");
		return new Person("marko", "markovic", 35);
	}
	
	
	@POST
	@Secured(role=AuthRole.ADMIN)
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
	public List<Person> getAllPersons() {
		System.out.println("GET ALL METHOD CALLED");
		return ((DataContext)ctx.getAttribute("data")).getPersons();
	}
	
	
	@POST
	@Path("/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.TEXT_PLAIN)
	public String uploadImage(
			@FormDataParam("image") InputStream image,
			@FormDataParam("image") FormDataContentDisposition imageDetails) {
		ImageHandler.saveImage(image, imageDetails, ctx.getRealPath(""));
		
		return "success";
	}
	
	
	private User getLoggedUser() {
		String username = sctx.getUserPrincipal().getName();
		return getDataContext().getUsers().get(username);
	}
	
	
	private DataContext getDataContext() {
		return (DataContext) ctx.getAttribute("data");
	}
	
}
