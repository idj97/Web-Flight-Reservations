package controller;

import java.io.File;
import java.io.InputStream;

import javax.servlet.ServletContext;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.SecurityContext;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import common.ImageHandler;
import common.RandomStringGenerator;
import dto.ResponseDTO;
import dto.UserDTO;
import model.DataContext;
import model.User;
import security.LoggedUser;
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
			String serverPath = getPath() + imageDesc.getFileName();
			String relPath = getRelPath() + imageDesc.getFileName();
			ImageHandler.saveImage(image, imageDesc, serverPath);
			User u = new User(name, surname, phone, email, relPath, username, password);
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
		if (u != null && u.getPassword().equals(password) && !u.isBlocked() && !getDataContext().getActiveTokens().containsValue(u)) {
			String token = RandomStringGenerator.get();
			getDataContext().getActiveTokens().put(token, u);
			System.out.println("Logged user count:" + getDataContext().getActiveTokens().size());
			return Response.ok(new ResponseDTO<UserDTO>(new UserDTO(u, token), "Login success.")).build();
		}
		return Response.status(Response.Status.BAD_REQUEST)
				.entity(new ResponseDTO<Object>(null, "Bad credientals.")).build();
	}
	
	
	
	@POST
	@Secured
	@Path("/logout")
	public Response logout() {
		String token = getToken();
		getDataContext().getActiveTokens().remove(token);
		System.out.println("Logged user count:" + getDataContext().getActiveTokens().size());
		return Response.ok().build();
	}

	
	
	@GET
	@Secured
	@Path("/details")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDetails() {
		User u = getLoggedUser();
		String token = getToken();
		return Response.ok(new ResponseDTO<UserDTO>(new UserDTO(u, token), "no-message")).build();
	}
	
	
	
	@PUT
	@Secured
	@Path("/change-password/{old}/{fresh}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response changePassword(@NotBlank @PathParam("old") String old, @NotBlank @PathParam("fresh") String fresh) {
		User u = getLoggedUser();
		if (u.getPassword().equals(old)) {
			u.setPassword(fresh);
			return Response.status(Status.OK).build();
		}
		return Response.status(Status.BAD_REQUEST).build();
	}
	
	
	
	@PUT
	@Secured
	@Path("/edit-profile")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response editProfile(@Valid UserDTO dto) {
		User u = getLoggedUser();
		u.setEmail(dto.getEmail());
		u.setUname(dto.getName());
		u.setSurname(dto.getSurname());
		u.setPhone(dto.getPhone());
		return Response.ok().build();
	}
	
	
	private DataContext getDataContext() {
		return (DataContext) ctx.getAttribute("data");
	}
	
	
	private User getLoggedUser() {
		return ((LoggedUser) sctx.getUserPrincipal()).getUser();
	}
	
	
	private String getToken() {
		return ((LoggedUser) sctx.getUserPrincipal()).getToken();
	}
	
	
	private String getPath() {
		return ctx.getRealPath("") + "img" + File.separator + "users" + File.separator;
	}
	
	
	private String getRelPath() {
		return File.separator + "WebProjekat" + 
	           File.separator + "img" + 
	           File.separator + "users" + 
			   File.separator;
	}
 	
}
