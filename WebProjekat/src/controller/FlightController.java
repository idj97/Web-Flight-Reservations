package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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

import dto.FlightDTO;
import model.DataContext;
import model.Destination;
import model.Flight;

@Path("/flights")
public class FlightController {

	@Context
	ServletContext ctx;
	
	
	@Context
	SecurityContext sctx;
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll() {
		List<FlightDTO> results = new ArrayList<>();
		for (Flight f : getDataContext().getFlights().values()) {
			results.add(new FlightDTO(f));
		}
		return Response.ok(results).build();
	}
	
	
	@POST
	//@Secured(role = AuthRole.ADMIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(@Valid FlightDTO dto) {
		if (verifyCreate(dto)) {
			Destination start = getDataContext().getDestinations().get(dto.getStart());
			Destination end = getDataContext().getDestinations().get(dto.getEnd());
			
			Date date = null;
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			try { date = sdf.parse(dto.getDate()); } 
			catch (ParseException e) { return Response.status(Status.BAD_REQUEST).build(); }
			
			Flight f = new Flight(dto, start, end, date);
			start.getStartFlights().put(f.getNumber(), f);
			end.getEndFlights().put(f.getNumber(), f);
			getDataContext().getFlights().put(f.getNumber(), f);
			return Response.ok().build();
		}
		return Response.status(Status.BAD_REQUEST).build();
	}
	
	
	
	@PUT
	//@Secured(role = AuthRole.ADMIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response edit(@Valid FlightDTO dto) {
		if (verifyEdit(dto)) {
			Flight f = getDataContext().getFlights().get(dto.getNumber());
			Destination start = getDataContext().getDestinations().get(dto.getStart());
			Destination end = getDataContext().getDestinations().get(dto.getEnd());
			
			Date date = null;
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			try { date = sdf.parse(dto.getDate()); } 
			catch (ParseException e) { return Response.status(Status.BAD_REQUEST).build(); }
			
			f.setAirplane(dto.getAirplane());
			f.setDate(date);
			f.setStart(start);
			f.setEnd(end);
			f.setBusinessSize(dto.getBusinessSize());
			f.setFirstSize(dto.getFirstSize());
			f.setEconomySize(dto.getEconomySize());
			f.setType(dto.getType());
			f.setPrice(dto.getPrice());
			
			return Response.ok().build();
		}
		return Response.status(Status.BAD_REQUEST).build();
	}
	
	
	
	
	@DELETE
	//@Secured(role = AuthRole.ADMIN)
	@Path("/{number}")
	public Response delete(@NotBlank @PathParam("number") String number) {
		if (verifyDelete(number)) {
			Flight f = getDataContext().getFlights().get(number);
			f.getStart().getStartFlights().remove(number);
			f.getEnd().getEndFlights().remove(number);
			getDataContext().getFlights().remove(number);
			return Response.ok().build();
		}
		return Response.status(Status.BAD_REQUEST).build();
	}
	
	
	
	
	
	@GET
	@Path("/search/{start}/{end}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response basicSearch(@NotBlank @PathParam("start") String start, @NotBlank @PathParam("end") String end) {
		List<FlightDTO> results = new ArrayList<>();
		for (Flight f : getDataContext().getFlights().values()) {
			if (searchHit(f, start, end)) 
				results.add(new FlightDTO(f));
		}
		return Response.ok().build();
	}
	
	
	
	
	
	private boolean searchHit(Flight f, String start, String end) {
		start = start.toLowerCase();
		end = end.toLowerCase();
		if (f.getStart().getName().toLowerCase().contains(start) || f.getStart().getState().toLowerCase().contains(start) ||
				f.getEnd().getName().toLowerCase().contains(end) || f.getEnd().getState().toLowerCase().contains(end))
			return true;
		return true;
	}
	
	
	
	private DataContext getDataContext() {
		return (DataContext) ctx.getAttribute("data");
	}
	

	
	private boolean verifyCreate(FlightDTO dto) {
		if (getDataContext().getFlights().containsKey(dto.getNumber()))
			return false;
		
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/YYYY");
		try {
			sdf.parse(dto.getDate());
		} catch (ParseException e) { 
			return false; 
		}
		
		if (dto.getStart().equals(dto.getEnd()))
			return false;
		
		if (!getDataContext().getDestinations().containsKey(dto.getStart()))
			return false;
		
		if (!getDataContext().getDestinations().containsKey(dto.getStart()))
			return false;
		
		return true;
	}
	
	
	private boolean verifyEdit(FlightDTO dto) {
		if (!getDataContext().getFlights().containsKey(dto.getNumber()))
			return false;
		
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/YYYY");
		try {
			sdf.parse(dto.getDate());
		} catch (ParseException e) { 
			return false; 
		}
		
		if (dto.getStart().equals(dto.getEnd()))
			return false;
		
		if (!getDataContext().getDestinations().containsKey(dto.getStart()))
			return false;
		
		if (!getDataContext().getDestinations().containsKey(dto.getStart()))
			return false;
		
		if (getDataContext().getFlights().get(dto.getNumber()).getReservations().size() > 0)
			return false;
		
		return true;
	}
	
	
	
	private boolean verifyDelete(String number) {
		if (!getDataContext().getFlights().containsKey(number))
			return false;
		
		if (getDataContext().getFlights().get(number).getReservations().size() > 0)
			return false;
		
		return true;
	}
	
	
	
	
}