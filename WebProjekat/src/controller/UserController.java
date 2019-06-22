package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.SecurityContext;

import dto.UserDTO;
import model.DataContext;
import model.User;
import security.AuthRole;
import security.Secured;

@Path("/users")
public class UserController {
	
	@Context
	private ServletContext ctx; 
	
	@Context
	private SecurityContext sctx;
	
	@GET
	@Secured(role=AuthRole.ADMIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Response get() {
		List<UserDTO> users = new ArrayList<>();
		for (User u : getDataContext().getUsers().values()) {
			UserDTO dto = new UserDTO();
			dto.setUsername(u.getUsername());
			dto.setName(u.getUname());
			dto.setEmail(u.getEmail());
			dto.setPhone(u.getPhone());
			dto.setSurname(u.getSurname());
			dto.setBlocked(u.isBlocked());
			users.add(dto);
		}
		return Response.ok(users).build();
	}
	
	
	@PUT
	@Path("/block/{username}/{block}")
	@Secured(role=AuthRole.ADMIN)
	public Response block(@NotBlank @PathParam("username") String username, @NotNull @PathParam("block") Boolean block) {
		User u = getDataContext().getUsers().get(username);
		if (u != null) {
			u.setBlocked(block);
			return Response.ok().build();
		}
		return Response.status(Status.BAD_REQUEST).build();
	}

	
	private DataContext getDataContext() {
		return (DataContext) ctx.getAttribute("data");
	}
	
}
