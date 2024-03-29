package controller;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.SecurityContext;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import common.ImageHandler;
import dto.DestinationDTO;
import model.DataContext;
import model.Destination;
import security.AuthRole;
import security.Secured;

@Path("/destinations")
public class DestinationController {
	
	@Context
	ServletContext ctx;
	
	@Context
	SecurityContext sctx;
	
	
	@GET
	@Secured(role = AuthRole.ADMIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Response get() {
		List<DestinationDTO> results = new ArrayList<>();
		for (Destination d : getDataContext().getDestinations().values()) {
			results.add(new DestinationDTO(d));
		}
		return Response.ok(results).build();
	}
	
	
	
	@POST
	@Secured(role=AuthRole.ADMIN)
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(
			@NotBlank @FormDataParam("name") String name,
			@NotBlank @FormDataParam("state") String state,
			@NotBlank @FormDataParam("airportName") String airportName,
			@NotBlank @FormDataParam("airportCode") String airportCode,
			@NotNull @FormDataParam("lat") Float lat,
			@NotNull @FormDataParam("log") Float log,
			@NotNull @FormDataParam("image") InputStream image,
			@NotNull @FormDataParam("image") FormDataContentDisposition imageDesc) {
		if (getDataContext().getDestinations().get(name) == null) {
			System.out.println(image + " " + imageDesc);
			String serverPath = getPath() + imageDesc.getFileName();
			String relPath = getRelPath() + imageDesc.getFileName();
			ImageHandler.saveImage(image, imageDesc, serverPath);
			System.out.println("Server path:" + serverPath);
			System.out.println("Rel path:" + relPath);
			Destination d = new Destination(name, state, airportName, airportCode, lat, log, relPath);
			getDataContext().getDestinations().put(name, d);
			return Response.ok(new DestinationDTO(d)).build();
		}
		return Response.status(Status.BAD_REQUEST).build();
	}
	

	
	@PUT
	@Secured(role=AuthRole.ADMIN) 
	@Path("/setImage")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response setImage(
			@NotBlank @FormDataParam("name") String name,
			@NotNull @FormDataParam("image") InputStream image,
			@NotNull @FormDataParam("imageDesc") FormDataContentDisposition imageDesc) {
		Destination d = getDataContext().getDestinations().get(name);
		if (d != null) {
			String serverPath = getPath() + imageDesc.getFileName();
			String relPath = getRelPath() + imageDesc.getFileName();
			ImageHandler.saveImage(image, imageDesc, serverPath);
			d.setPicturePath(relPath);
			return Response.ok(new DestinationDTO(d)).build();
		}
		return Response.status(Status.BAD_REQUEST).build();
	}
	
	

	@PUT
	@Secured(role = AuthRole.ADMIN)
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response editDestination(@Valid DestinationDTO dest) {
		Destination d = getDataContext().getDestinations().get(dest.getName());
		if (d != null) {
			d.setState(dest.getState());
			d.setAirportName(dest.getAirportName());
			d.setAirportCode(dest.getAirportCode());
			d.setLat(dest.getLat());
			d.setLog(dest.getLog());
			d.setArchived(dest.getArchived());
			return Response.ok().build();
		}
		return Response.status(Status.BAD_REQUEST).build();
	}
	
	
	private DataContext getDataContext() {
		return (DataContext) ctx.getAttribute("data");
	}
	
	
	private String getPath() {
		return ctx.getRealPath("") + "img" + File.separator + "destinations" + File.separator;
	}
	
	
	private String getRelPath() {
		return File.separator + "WebProjekat" + 
	           File.separator + "img" + 
	           File.separator + "destinations" + 
			   File.separator;
	}
 	
}
