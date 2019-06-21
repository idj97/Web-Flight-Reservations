package security;

import java.security.Principal;

import model.User;

public class LoggedUser implements Principal {
	private User user;
	private String token;

	public LoggedUser() {
		super();
	}

	public LoggedUser(User user, String token) {
		super();
		this.user = user;
		this.token = token;
	}

	@Override
	public String getName() {
		return user.getUname();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
