package model;

import java.io.Serializable;
import java.security.Principal;

import security.AuthRole;

public class User implements Principal, Serializable {
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private AuthRole role;
	private transient String token;

	public User() {
		super();
	}

	public User(String username, String password, AuthRole role) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;
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

}
