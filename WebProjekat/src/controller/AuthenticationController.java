package controller;

import java.io.InputStream;

import javax.servlet.ServletContext;
import javax.validation.constraints.NotBlank;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import common.ImageHandler;
import common.RandomStringGenerator;
import dto.ResponseDTO;
import dto.UserDTO;
import model.DataContext;
import model.User;
import security.Secured;

@Path("/auth")
public class AuthenticationController {
	
	@Context
	ServletContext ctx;
	
	
	@Context
	SecurityContext sctx;
	
	
	@POST
	@Path("/register")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public Response register(
			@NotBlank @FormDataParam("username") String username, 
			@NotBlank @FormDataParam("password") String password,
			@NotBlank @FormDataParam("name") String name,
			@NotBlank @FormDataParam("surname") String surname,
			@NotBlank @FormDataParam("phone") String phone,
			@NotBlank @FormDataParam("email") String email,
			@FormDataParam("image") InputStream image,
			@FormDataParam("image") FormDataContentDisposition imageDesc) {
		
		if (getDataContext().getUsers().get(username) == null) {
			String path = ctx.getRealPath("") + "/img/" + imageDesc.getFileName();
			ImageHandler.saveImage(image, imageDesc, path);
			User u = new User(name, surname, phone, email, path, username, password);
			getDataContext().getUsers().put(username, u);	
			return Response
					.ok(new ResponseDTO<String>(null, "Registration successfull."))
					.build();
		}
		
		return Response
				.status(Response.Status.BAD_REQUEST)
				.entity(new ResponseDTO<Object>(null, "Username is already taken."))
				.build();
	}
		
	
	@POST
	@Path("/login/{username}/{password}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response authenticate(@NotBlank @PathParam("username") String username, @NotBlank @PathParam("password") String password) {
		User u = getDataContext().getUsers().get(username);
		if (u != null && u.getPassword().equals(password) && !u.isBlocked() && u.getToken() == null) {
			String token = RandomStringGenerator.get();
			u.setToken(token);
			return Response.ok(new ResponseDTO<UserDTO>(new UserDTO(u), "Login success.")).build();
		}
		return Response.status(Response.Status.BAD_REQUEST)
				.entity(new ResponseDTO<Object>(null, "Bad credientals.")).build();
	}
	
	
	@POST
	@Secured
	@Path("/logout")
	public Response logout() {
		User u = getLoggedUser();
		u.setToken(null);
		return Response.noContent().build();
	}
	
	
	@GET
	@Secured
	@Path("/details")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDetails() {
		User u = getLoggedUser();
		return Response.ok(new ResponseDTO<UserDTO>(new UserDTO(u), "no-message")).build();
	}
	
	
	private DataContext getDataContext() {
		return (DataContext) ctx.getAttribute("data");
	}
	
	
	private User getLoggedUser() {
		String username = sctx.getUserPrincipal().getName();
		return getDataContext().getUsers().get(username);
	}
}
