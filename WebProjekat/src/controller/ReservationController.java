package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.SecurityContext;

import common.SequenceNumberGenerator;
import dto.ReservationDTO;
import model.DataContext;
import model.Flight;
import model.Reservation;
import model.ReservationType;
import model.User;
import security.LoggedUser;
import security.Secured;

@Path("/reservations")
public class ReservationController {

	@Context
	ServletContext ctx;
	
	
	@Context
	SecurityContext sctx;
	
	
	
	@GET
	@Secured
	@Produces(MediaType.APPLICATION_JSON)
	public Response get() {
		User u = getLoggedUser();
		List<ReservationDTO> reservations = new ArrayList<>();
		for (Reservation r : u.getReservations().values()) {
			reservations.add(new ReservationDTO(r));
		}
		return Response.ok(reservations).build();
	}
	
	
	
	
	@POST
	@Secured
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(@Valid ReservationDTO dto) {
		if (verifyCreate(dto)) {
			Long reservationId = SequenceNumberGenerator.incrementAndGet();
			Reservation r = new Reservation(reservationId, dto.getType());
			User u = getLoggedUser();
			r.setOwner(u);
			u.getReservations().put(reservationId, r);
			
			Flight f = getDataContext().getFlights().get(dto.getFlightNum());
			f.getReservations().put(reservationId, r);
			
			if (dto.getType().equals(ReservationType.FIRST))
				f.setFirstSize(f.getFirstSize() - dto.getPassengerNum());
			else if (dto.getType().equals(ReservationType.BUSINESS))
				f.setBusinessSize(f.getBusinessSize() - dto.getPassengerNum());
			else
				f.setEconomySize(f.getEconomySize() - dto.getPassengerNum());
			
			r.setFlight(f);
			r.setPassengerNumber(dto.getPassengerNum());
			return Response.ok(new ReservationDTO(r)).build();
		}
		return Response.status(Status.BAD_REQUEST).build();
	}
	
	
	
	
	@DELETE
	@Secured
	@Path("/cancel/{resId}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response cancel(@PathParam("resId") Long resId) {
		User u = getLoggedUser();
		if (u.getReservations().containsKey(resId)) {
			Reservation r = u.getReservations().get(resId);
			Flight f = r.getFlight();
			u.getReservations().remove(resId);
			f.getReservations().remove(resId);
			
			if (r.getType().equals(ReservationType.FIRST))
				f.setFirstSize(f.getFirstSize() + r.getPassengerNumber());
			else if (r.getType().equals(ReservationType.BUSINESS))
				f.setBusinessSize(f.getBusinessSize() + r.getPassengerNumber());
			else
				f.setEconomySize(f.getEconomySize() + r.getPassengerNumber());
			
			
			return Response.ok().build();
		}
		return Response.status(Status.BAD_REQUEST).build();
	}
	
	
	
	public boolean isPassenger(String flightId) {
		Flight f = getDataContext().getFlights().get(flightId);
		User u = getLoggedUser();
		for (Reservation r : f.getReservations().values())
			if (r.getOwner() == u)
				return true;
		return false;
	}
	
	
	
	
	private boolean verifyCreate(ReservationDTO dto) {
		Flight f = getDataContext().getFlights().get(dto.getFlightNum());
		if (f == null)
			return false;
		
		if (isPassenger(dto.getFlightNum())) 
			return false;
		
		if (dto.getType().equals(ReservationType.FIRST))
			return f.getFirstSize() >= dto.getPassengerNum();
		else if (dto.getType().equals(ReservationType.BUSINESS))
			return f.getBusinessSize() >= dto.getPassengerNum();
		else
			return f.getEconomySize() >= dto.getPassengerNum();
	}
	
	
	
	private DataContext getDataContext() {
		return (DataContext) ctx.getAttribute("data");
	}
	
	
	
	private User getLoggedUser() {
		return ((LoggedUser) sctx.getUserPrincipal()).getUser();
	}
	
}
