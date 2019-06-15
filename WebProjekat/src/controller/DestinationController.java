package controller;

import java.io.File;
import java.io.InputStream;

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
import dto.EditDestinationDTO;
import model.DataContext;
import model.Destination;

@Path("/destinations")
public class DestinationController {
	
	@Context
	ServletContext ctx;
	
	@Context
	SecurityContext sctx;
	
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response get() {
		return Response.ok(getDataContext().getDestinations().values()).build();
	}
	
	
	
	@POST
	//@Secured(role=AuthRole.ADMIN)
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
			@NotNull @FormDataParam("imageDesc") FormDataContentDisposition imageDesc) {
		if (getDataContext().getDestinations().get(name) == null) {
			String path = getPath() + imageDesc.getFileName();
			ImageHandler.saveImage(image, imageDesc, path);
			Destination d = new Destination(name, state, airportName, airportCode, lat, log, path);
			getDataContext().getDestinations().put(name, d);
			return Response.ok(d).build();
		}
		return Response.status(Status.BAD_REQUEST).build();
	}
	

	
	@PUT
	//@Secured(role=AuthRole.ADMIN) 
	@Path("/setImage")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response setImage(
			@NotBlank @FormDataParam("name") String name,
			@NotNull @FormDataParam("image") InputStream image,
			@NotNull @FormDataParam("imageDesc") FormDataContentDisposition imageDesc) {
		Destination d = getDataContext().getDestinations().get(name);
		if (d != null) {
			String path = getPath() + imageDesc.getFileName();
			ImageHandler.saveImage(image, imageDesc, path);
			d.setPicturePath(path);
			return Response.ok(d).build();
		}
		return Response.status(Status.BAD_REQUEST).build();
	}
	
	

	@PUT
	//@Secured(role = AuthRole.ADMIN)
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response editDestination(@Valid EditDestinationDTO dest) {
		Destination d = getDataContext().getDestinations().get(dest.getOldName());
		if (d != null) {
			d.setName(dest.getName());
			d.setState(dest.getState());
			d.setAirportName(dest.getAirportName());
			d.setAirportCode(dest.getAirportCode());
			d.setLat(dest.getLat());
			d.setLog(dest.getLog());
			d.setArchived(dest.getArchived());
			return Response.ok(d).build();
		}
		return Response.status(Status.BAD_REQUEST).build();
	}
	
	
	
	private DataContext getDataContext() {
		return (DataContext) ctx.getAttribute("data");
	}
	
	
	
	private String getPath() {
		return ctx.getRealPath("") + File.pathSeparator + "img" + File.pathSeparator + "destinations" + File.pathSeparator;
	}
	
}
