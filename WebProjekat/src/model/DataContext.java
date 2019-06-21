package model;

import java.io.Serializable;
import java.util.concurrent.ConcurrentHashMap;

public class DataContext implements Serializable {
	private static final long serialVersionUID = 1L;

	private ConcurrentHashMap<String, User> activeTokens;
	private ConcurrentHashMap<String, User> users;
	private ConcurrentHashMap<String, Destination> destinations;
	private ConcurrentHashMap<String, Flight> flights;
	private Long reservationNumber;

	public DataContext() {
		super();
	}

	public void init() {
		users = new ConcurrentHashMap<>();
		destinations = new ConcurrentHashMap<>();
		flights = new ConcurrentHashMap<>();
		activeTokens = new ConcurrentHashMap<>();
		reservationNumber = (long) 0;
	}

	public ConcurrentHashMap<String, User> getUsers() {
		return users;
	}

	public void setUsers(ConcurrentHashMap<String, User> users) {
		this.users = users;
	}

	public ConcurrentHashMap<String, Destination> getDestinations() {
		return destinations;
	}

	public void setDestinations(ConcurrentHashMap<String, Destination> destinations) {
		this.destinations = destinations;
	}

	public ConcurrentHashMap<String, Flight> getFlights() {
		return flights;
	}

	public void setFlights(ConcurrentHashMap<String, Flight> flights) {
		this.flights = flights;
	}

	public ConcurrentHashMap<String, User> getActiveTokens() {
		return activeTokens;
	}

	public Long getReservationNumber() {
		return reservationNumber;
	}

	public void setReservationNumber(Long reservationNumber) {
		this.reservationNumber = reservationNumber;
	}

	public void setActiveTokens(ConcurrentHashMap<String, User> activeTokens) {
		this.activeTokens = activeTokens;
	}

}
