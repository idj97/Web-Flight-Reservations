package model;

import java.io.Serializable;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import security.AuthRole;

public class User implements Principal, Serializable {
	private static final long serialVersionUID = 1L;
	private String uname;
	private String surname;
	private String phone;
	private String email;
	private String picturePath;
	private boolean blocked;
	private List<Reservation> reservations;

	private String username;
	private String password;
	private AuthRole role;
	private transient String token;

	public User() {
		super();
	}
	
	public User(String uname, String surname, String phone, String email, String picturePath,
			String username, String password) {
		super();
		this.uname = uname;
		this.surname = surname;
		this.phone = phone;
		this.email = email;
		this.picturePath = picturePath;
		this.blocked = false;
		this.username = username;
		this.password = password;
		this.role = AuthRole.USER;
		this.reservations = new ArrayList<Reservation>();
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPicturePath() {
		return picturePath;
	}

	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}

	public boolean isBlocked() {
		return blocked;
	}

	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public AuthRole getRole() {
		return role;
	}

	public void setRole(AuthRole role) {
		this.role = role;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String getName() {
		return username;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

}
