package dto;

import model.User;
import security.AuthRole;

public class UserDTO {
	private String name;
	private String surname;
	private String phone;
	private String email;
	private String picturePath;
	private String token;
	private AuthRole role;

	public UserDTO() {
		super();
	}

	public UserDTO(String name, String surname, String phone, String email, String picturePath, String token,
			AuthRole role) {
		super();
		this.name = name;
		this.surname = surname;
		this.phone = phone;
		this.email = email;
		this.picturePath = picturePath;
		this.token = token;
		this.role = role;
	}
	
	public UserDTO(User u) {
		this(u.getUname(), u.getSurname(), u.getPhone(), u.getEmail(), u.getPicturePath(), u.getToken(), u.getRole());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public AuthRole getRole() {
		return role;
	}

	public void setRole(AuthRole role) {
		this.role = role;
	}

}
