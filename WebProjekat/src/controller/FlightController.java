package controller;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import model.DataContext;

@Path("/flights")
public class FlightController {

	@Context
	ServletContext ctx;
	
	@Context
	SecurityContext sctx;
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll() {
		return Response.ok(getDataContext().getFlights().values()).build();
	}
	
	
	private DataContext getDataContext() {
		return (DataContext) ctx.getAttribute("data");
	}
	
}
