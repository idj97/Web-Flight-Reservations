package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class DataContext implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<Person> persons;
	
	private ConcurrentHashMap<String, User> users;
	private ConcurrentHashMap<String, Destination> destinations;
	private ConcurrentHashMap<String, Flight> flights;
	
	public DataContext() {
		super();
	}

	public void init() {
		persons = new ArrayList<>();
		users = new ConcurrentHashMap<>();
		destinations = new ConcurrentHashMap<>();
		flights = new ConcurrentHashMap<>();
	}

	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
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

}
