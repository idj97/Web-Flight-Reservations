package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DataContext implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<Person> persons;
	private List<User> users;
	
	public DataContext() {
		super();
	}
	
	public void init() {
		persons = new ArrayList<>();
		users = new ArrayList<>();
	}

	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	

}
