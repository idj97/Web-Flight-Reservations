package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class DataContext implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<Person> persons;
	private ConcurrentHashMap<String, User> users;

	public DataContext() {
		super();
	}

	public void init() {
		persons = new ArrayList<>();
		users = new ConcurrentHashMap<>();
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

}
