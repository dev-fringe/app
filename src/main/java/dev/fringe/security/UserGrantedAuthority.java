package dev.fringe.security;

import org.springframework.security.core.GrantedAuthority;

public class UserGrantedAuthority implements GrantedAuthority {
	private static final long serialVersionUID = -3786297951121082644L;

	private String authority = null;

	public UserGrantedAuthority(String auth) {
		authority = auth;
	}

	public String getAuthority() {
		return authority;
	}
}
