package dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import model.User;
import security.AuthRole;

public class UserDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	@NotBlank private String name;
	@NotBlank private String surname;
	@NotBlank private String phone;
	@NotBlank private String email;
	private String picturePath;
	private String token;
	private String username;
	private AuthRole role;
	private boolean blocked;

	public UserDTO() {
		super();
	}

	public UserDTO(String username, String name, String surname, String phone, String email, String picturePath, String token,
			AuthRole role, boolean blocked) {
		super();
		this.username = username;
		this.name = name;
		this.surname = surname;
		this.phone = phone;
		this.email = email;
		this.picturePath = picturePath;
		this.token = token;
		this.role = role;
		this.blocked = blocked;
	}

	public UserDTO(User u, String token) {
		this(u.getUsername(), u.getUname(), u.getSurname(), u.getPhone(), u.getEmail(), u.getPicturePath(), token, u.getRole(),
				u.isBlocked());
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

	public boolean isBlocked() {
		return blocked;
	}

	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
