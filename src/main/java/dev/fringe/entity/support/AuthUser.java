package dev.fringe.entity.support;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class AuthUser implements Serializable, UserDetails {
	private static final long serialVersionUID = 1201392234549297485L;
	private long id;
	private String password;
	private String username;
	private GrantedAuthority[] authorities = null;

	public AuthUser(long id, String username, String password, GrantedAuthority[] authorities) {
		this.id = id;
		this.password = password;
		this.username = username;
		this.authorities = authorities;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();
		Collections.addAll(auth, authorities);
		return auth;
	}

	public String getPassword() {
		return this.password;
	}

	public String getUsername() {
		return this.username;
	}

	public boolean isAccountNonExpired() {
		return true;
	}

	public boolean isAccountNonLocked() {
		return true;
	}

	public boolean isCredentialsNonExpired() {
		return true;
	}

	public boolean isEnabled() {
		return true;
	}

	public String toString() {
		return "CustomUser [id=" + id + ", password=" + password + ", username=" + username + ", authorities="
				+ Arrays.toString(authorities) + "]";
	}

}
