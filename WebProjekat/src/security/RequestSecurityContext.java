package security;

import java.security.Principal;

import javax.ws.rs.core.SecurityContext;

import model.User;

public class RequestSecurityContext implements SecurityContext {
	private User user;
	private boolean secure;

	public RequestSecurityContext() {
		super();
	}

	public RequestSecurityContext(User user, boolean secure) {
		super();
		this.user = user;
		this.secure = secure;
	}

	@Override
	public String getAuthenticationScheme() {
		return "Bearer";
	}

	@Override
	public Principal getUserPrincipal() {
		return user;
	}

	@Override
	public boolean isSecure() {
		return secure;
	}

	@Override
	public boolean isUserInRole(String arg0) {
		return true;
	}

}
